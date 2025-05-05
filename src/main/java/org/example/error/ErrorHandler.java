package org.example.error;


import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;

/*Два класса ошибок - семантические (не останавливают парсер, выдают сообщения)
    Второй - парсерные ошибки - завершают работу программы
 */
public class ErrorHandler {
    private static int CountError = 0;

    private final static String ERROR_TEXT = "Семантическая ошибка в строке ";

    private final static String ID_IS_USER = "Повторное объявление переменной ";

    private final static String ID_IS_NOT_FOUND = "Переменная не объявлена ";
    private final static String TYPE_ERROR = "Неверное значение выражения ";
    private final static String ITERATOR = "Неверный тип итератора. Допускается только целое ";
    private final static String RETURN = "Ошибка с блоком возврата ";

    private final static String DUPLICATE_PARAM = "Дублирующийся параметр функции ";

    private final static String INDEX_OUT_OF_ARRAY = "Элемент находится вне границ массива ";

    private final static String ARRAY_SIZE_ERROR = "Количество элементов массива не совпадают с размером массива ";

    private final static String SEMANTIC_ERROR_IS_FOUND= "Присутствуют семантические ошибки. Процесс компиляции остановлен. ";

    private final static String BREAK_CONTINUE_ERROR ="Оператор пропустить или стоп объявлены вне цикла";

    private final static String PARAM_ERROR ="Неожиданный параметр при задаче функции";

    private final static String PARAM_ERROR_NEDOSTATOCHNO ="Количество параметров функции не совпадает с сигнатурой функции";
    private ArrayList<SemanticError> errors = new ArrayList<>();
    private static ErrorHandler errorHandler;


    public static synchronized ErrorHandler getErrorHandler(){
        if(errorHandler == null){
            errorHandler = new ErrorHandler();
        }
        return errorHandler;
    }

    private void Check(){
        if(CountError > 0){
            System.err.println(SEMANTIC_ERROR_IS_FOUND);
            System.exit(1);
        }
    }

    public void ErrorSemIDIsUsed(ParserRuleContext context, String additionalInfo){
        ErrorSem(ID_IS_USER, context, additionalInfo);
    }
    public void ErrorSemParamIsLishniy(ParserRuleContext context, String additionalInfo){
        ErrorSem(PARAM_ERROR, context, additionalInfo);
    }

    public void ErrorSemParamIsNedostatochno(ParserRuleContext context, String additionalInfo){
        ErrorSem(PARAM_ERROR_NEDOSTATOCHNO, context, additionalInfo);
    }
    public void ErrorSemBreakOrContinueOutLoop(ParserRuleContext context, String additionalInfo){
        ErrorSem(BREAK_CONTINUE_ERROR, context, additionalInfo);
    }

    public void ErrorSemIDNotFound(ParserRuleContext context, String additionalInfo){
        ErrorSem(ID_IS_NOT_FOUND, context, additionalInfo);
    }

    public void ErrorSemTypeError(ParserRuleContext context, String additionalInfo){
        ErrorSem(TYPE_ERROR, context, additionalInfo);
    }

    public void ErrorSemIterator(ParserRuleContext context, String additionalInfo){
        ErrorSem(ITERATOR, context, additionalInfo);
    }

    public void ErrorSemReturn(ParserRuleContext context, String additionalInfo){
        ErrorSem(RETURN, context, additionalInfo);
    }

    public void ErrorSemDuplicateParam(ParserRuleContext context, String additionalInfo){
        ErrorSem(DUPLICATE_PARAM, context, additionalInfo);
    }

    public void ErrorSemIndexOutOf(ParserRuleContext context, String additionalInfo){
        ErrorSem(INDEX_OUT_OF_ARRAY, context, additionalInfo);
    }


    public void ErrorSemArraySize(ParserRuleContext context, String additionalInfo){
        ErrorSem(ARRAY_SIZE_ERROR, context, additionalInfo);
    }

    private void ErrorSem(String message, ParserRuleContext context, String additionalInfo){
        int startLine = context.start.getLine();
        int startChar = context.start.getCharPositionInLine();
        int endChar = context.stop != null ? context.stop.getCharPositionInLine() : startChar;

        errors.add(new SemanticError(message+" "+additionalInfo, startLine, startChar, endChar));

        CountError++;
    }

    public ArrayList<SemanticError> getErrors() {
        return errors;
    }

    public void clearError(){
        errors.clear();
    }
}
