package org.example.IR;

import org.example.semantic.SymbolInfo;
import org.example.semantic.SymbolTable;
import org.example.semantic.TypeName;

import java.util.ArrayList;

public class DAG {
    private final ArrayList<Node> nodes;
    private final SymbolTable table;

    private final Block block;
    public DAG(Block block) {
        this.block = block;
        nodes = new ArrayList<>();
        table = block.getInstructions().get(0).getMyTable();
        for (Instructions instructions : block.getInstructions()) {
            boolean isLoopNode = instructions.myInLoop();
            Node left = instructions.getArg1() != null ? getNodeForVar(instructions.getArg1()) : null;
            Node right = instructions.getArg2() != null ? getNodeForVar(instructions.getArg2()) : null;

            if (left == null && instructions.getArg1() != null) {
                left = new Node(instructions.getArg1());
                addNode(left);
            }
            if (right == null && instructions.getArg2() != null) {
                right = new Node(instructions.getArg2());
                addNode(right);
            }

            Node operationNode = null;
            if (!isLoopNode){
                operationNode = findExistingNode(instructions.getOp(), left, right);
            }


            if (operationNode == null) {
                operationNode = new Node(instructions.getOp(), left, right, instructions.getCompOp());
                if (left != null) left.addParent(operationNode);
                if (right != null) right.addParent(operationNode);
                addNode(operationNode);
            }

            operationNode.setMyTable(instructions.getMyTable());
            if(isLoopNode){
                operationNode.setLoopNode(true);
            }
            if(instructions.isMyLogical()){
                operationNode.setLogical(true);
            }


            operationNode.addVar(instructions.getResult());
        }


        constant();
        removeUnusedNodes(block.getLiveVariables());
    }

    private void removeUnusedNodes(ArrayList<String> liveVariables) {
        int size = nodes.size();
        boolean isChange = true;
        while (isChange) {
            ArrayList<Node> toRemove = new ArrayList<>();

            for (Node node : nodes) {
                if(node.isVarNode){
                    continue;
                }
                if (node.operator.equals(Operator.PARAM)){
                    continue;
                }
                if (node.operator.equals(Operator.CALL) || node.operator.equals(Operator.RETURN) || node.operator.equals(Operator.IFFALSE) || node.operator.equals(Operator.IFTRUE) || node.operator.equals(Operator.PRINT) || node.isLoopNode || node.operator.equals(Operator.GOTO) || node.isLogical){
                    continue;
                }

                if (node.variables.stream().noneMatch(liveVariables::contains) && node.parents.isEmpty()) {
                    toRemove.add(node);
                }
            }

            for (Node node : toRemove) {
                for (Node child : nodes) {
                    child.parents.remove(node);
                }
            }
            nodes.removeAll(toRemove);

            if (size != nodes.size()) {
                size = nodes.size();
            } else {
                isChange = false;
            }
        }
    }

    private void constant(){
        for(Node node: nodes){
            if(node.isVarNode || node.isLoopNode || node.isLogical){
                continue;
            }
            if(node.operator.equals(Operator.ASSIGN)){
                if(node.left.isVarNode){
                    for(String var: node.variables){
                        SymbolInfo info = table.find(var);
                        if(info != null){
                            info.setValue(node.left.root);
                        }
                    }
                    node.root = node.left.root;
                    node.isVarNode = true;
                    node.left = null;
                    node.variables = new ArrayList<>();
                }
                continue;
            }
            constantFold(node);

        }

    }


    private void constantFold(Node node) {
        SymbolInfo leftInfo = null, rightInfo = null;

        if(node.left != null){
            leftInfo = table.find(node.left.root);
        }
        if(node.right != null){
            rightInfo = table.find(node.right.root);
        }



        if (leftInfo != null && leftInfo.getValue() != null &&
                rightInfo != null && rightInfo.getValue() != null)
        {
            if(leftInfo.isIterator() || rightInfo.isIterator()){
                return;
            }


            SymbolInfo left = table.find(node.left.root);
            SymbolInfo right = table.find(node.right.root);

            if (left == null || right == null) return;

            try {
                Object result = switch (node.operator) {
                    case PLUS -> compute("+", left, right);
                    case MINUS -> compute("-", left, right);
                    case MULTIPLY -> compute("*", left, right);
                    case DIVIDE -> compute("/", left, right);
                    default -> null;
                };

                if (result != null) {


                    node.root = String.valueOf(result);
                    node.left = null;
                    node.right = null;
                    node.variables.clear();
                    node.isVarNode = true;

                    if (result instanceof Integer) {
                        table.declareConstant(result.toString(), TypeName.INTEGER);
                    } else if (result instanceof Float) {
                        table.declareConstant(result.toString(), TypeName.FLOAT);
                    } else if (result instanceof String) {
                        table.declareConstant(result.toString(), TypeName.STRING);
                    }
                }

            } catch (Exception e) {

            }
        }
    }

    private Object compute(String op, SymbolInfo a, SymbolInfo b) {
        if (a.getType() == TypeName.INTEGER && b.getType() == TypeName.INTEGER) {
            int x = Integer.parseInt(a.getValue().toString());
            int y = Integer.parseInt(b.getValue().toString());

            return switch (op) {
                case "+" -> x + y;
                case "-" -> x - y;
                case "*" -> x * y;
                case "/" -> x / y;
                default -> null;
            };
        }
        if (a.getType() == TypeName.FLOAT && b.getType() == TypeName.FLOAT) {
            float x = Float.parseFloat(a.getValue().toString());
            float y = Float.parseFloat(b.getValue().toString());

            return switch (op) {
                case "+" -> x + y;
                case "-" -> x - y;
                case "*" -> x * y;
                case "/" -> x / y;
                default -> null;
            };
        }
        if (a.getType() == TypeName.INTEGER && b.getType() == TypeName.FLOAT) {
            float x = Float.parseFloat(a.getValue().toString());
            float y = Float.parseFloat(b.getValue().toString());

            return switch (op) {
                case "+" -> x + y;
                case "-" -> x - y;
                case "*" -> x * y;
                case "/" -> x / y;
                default -> null;
            };
        }

        if (a.getType() == TypeName.FLOAT && b.getType() == TypeName.INTEGER) {
            float x = Float.parseFloat(a.getValue().toString());
            float y = Float.parseFloat(b.getValue().toString());

            return switch (op) {
                case "+" -> x + y;
                case "-" -> x - y;
                case "*" -> x * y;
                case "/" -> x / y;
                default -> null;
            };
        }

        if (a.getType() == TypeName.STRING && b.getType() == TypeName.STRING) {
               return a.getValue().toString() + b.getValue().toString();
        }


        return null;
    }

    public Block generateNewBlock() {
        ArrayList<Instructions> instructions = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();

        for (Node node : nodes) {
            generateInstructions(node, instructions, visited);
        }

        return new Block(this.block, instructions);
    }
    private void generateInstructions(Node node, ArrayList<Instructions> instructions, ArrayList<Node> visited) {
        if (node == null || visited.contains(node) || node.isVarNode) return;


        generateInstructions(node.left, instructions, visited);
        generateInstructions(node.right, instructions, visited);

        String left = getResultFromNode(node.left);
        String right = getResultFromNode(node.right);
        String result = node.variables.isEmpty() ? node.root : node.variables.get(0);


        Instructions instructions1 =new Instructions(node.operator, left,node.comprassion, right, result, node.getMyTable());
        instructions1.setMyLogical(node.isLogical);
        instructions.add(instructions1);


        for (int i = 1; i < node.variables.size(); i++) {
            if(table.find(node.variables.get(i)) == null){
            continue;
            }

            instructions.add(new Instructions(Operator.ASSIGN, result, null, node.variables.get(i), table));
        }

        visited.add(node);
    }
    private String getResultFromNode(Node node){
        if(node == null){
            return null;
        }
        if(node.isVarNode){
            return node.root;
        }
        return node.variables.get(0);

    }

  /*  public void printDAG() {
        System.out.println("=== DAG ===");
        for (Node node : nodes) {
            System.out.print("Node: " + node.root);
            if (!node.variables.isEmpty()) {
                System.out.print(" (vars: " + node.variables + ")");
            }
            if (node.left != null) {
                System.out.print(" Left: " + node.left.root);
            }
            if (node.right != null) {
                System.out.print(" Right: " + node.right.root);
            }
            if (!node.parents.isEmpty()) {
                System.out.print(" Parents: ");
                for (Node parent : node.parents) {
                    System.out.print(parent.root + " ");
                }
            }
            System.out.println();
        }
        System.out.println("================");
    }
*/
    private Node getNode(String var) {
        for (Node node : nodes) {
            if (node.isVarNode && node.root.equals(var)) {
                return node;
            }
        }
        return null;
    }

    private Node getNodeForVar(String var) {
        for (int i = nodes.size()-1; i > 0; i--) {
            if (nodes.get(i).variables.contains(var)) {
                return nodes.get(i);
            }
        }
        return getNode(var);
    }

    private void addNode(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    private Node findExistingNode(String root, Node left, Node right) {
        for (Node node : nodes) {
            if (!node.isVarNode && node.root.equals(root) && node.left == left && node.right == right) {
                return node;
            }
        }
        return null;
    }

    private Node findExistingNode(Operator op, Node left, Node right) {
        if (op.equals(Operator.PARAM) || op.equals(Operator.PRINT)){
            return null;
        }
        if(op.equals(Operator.PLUS) || op.equals(Operator.MULTIPLY)){
            return findExistingNodeCom(op, left, right);
        }

        for (Node node : nodes) {
            if (!node.isVarNode && node.operator.equals(op) && node.left == left && node.right == right) {
                return node;
            }
        }
        return null;
    }


    private Node findExistingNodeCom(Operator op, Node left, Node right){
        for (Node node : nodes) {
            if (!node.isVarNode && node.operator.equals(op) &&
                    ((node.left == left && node.right == right) || (node.right == left && node.left == right))) {
                return node;
            }
        }


        return null;
    }

    class Node {
        private final ArrayList<Node> parents;
        private ArrayList<String> variables;
        private String root;
        private Node left;
        private Node right;
        private Operator operator;
        private boolean isVarNode;

        private boolean isLoopNode = false;

        private Operator comprassion;

        private boolean isLogical = false;

        private SymbolTable myTable;

        public void setLoopNode(boolean loopNode) {
            isLoopNode = loopNode;
        }

        public boolean isLogical() {
            return isLogical;
        }

        public void setLogical(boolean logical) {
            isLogical = logical;
        }

        public boolean isLoopNode() {
            return isLoopNode;
        }

        public Node(Operator op, Node left, Node right, Operator comprassion) {
            operator = op;
            this.comprassion = comprassion;
            this.left = left;
            this.right = right;
            this.isVarNode = false;
            this.parents = new ArrayList<>();
            this.variables = new ArrayList<>();
        }

        public Node(String root) {
            this.root = root;
            this.isVarNode = true;
            this.parents = new ArrayList<>();
            this.variables = new ArrayList<>();
        }

        public void addParent(Node parent) {
            parents.add(parent);
        }

        public void addVar(String var) {
            if (!variables.contains(var)) {
                variables.add(var);
            }
        }

        public SymbolTable getMyTable() {
            return myTable;
        }

        public void setMyTable(SymbolTable myTable) {
            this.myTable = myTable;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("Node {");
            sb.append("\n  root: ").append(root);
            sb.append("\n  isVarNode: ").append(isVarNode);
            sb.append("\n  parents: ").append(parentsToString());
            sb.append("\n  variables: ").append(variablesToString());

            if (left != null) {
                sb.append("\n  left: ").append(left.root);
            } else {
                sb.append("\n  left: null");
            }

            if (right != null) {
                sb.append("\n  right: ").append(right.root);
            } else {
                sb.append("\n  right: null");
            }

            sb.append("\n}");

            return sb.toString();
        }

        private String parentsToString() {
            if (parents.isEmpty()) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < parents.size(); i++) {
                sb.append(parents.get(i).root);
                if (i < parents.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");

            return sb.toString();
        }

        private String variablesToString() {
            if (variables.isEmpty()) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < variables.size(); i++) {
                sb.append(variables.get(i));
                if (i < variables.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");

            return sb.toString();
        }
    }
}