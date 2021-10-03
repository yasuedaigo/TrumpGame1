import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrumpGame {
    private static enum Phase {
        Mark, Number
    };

    private static int inputNumber;
    private static final Random RANDOM = new Random();
    private static final Scanner STDIN = new Scanner(System.in);
    private static int offSetSelectNumberInt = -1;
    private static int startcount = 0;
    private static final List<String> MARKLIST = new ArrayList<>() {
        {
            add("ダイヤ");
            add("スペード");
            add("クローバー");
            add("ハート");
        }
    };
    private static final List<String> NUMBERLIST = new ArrayList<>() {
        {
            add("A");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
            add("J");
            add("Q");
            add("K");
        }
    };

    private static final String INTRODUCTION_MESSAGE = "トランプを選んだよ";
    private static final String QUESTION_MESSAGE = "どれだと思う？ : ";
    private static final String MARK_QUESTION_MESSAGE = "トランプの図柄を当ててね";
    private static final String NUMBER_QUESTION_MESSAGE = "次は数字を当ててね";

    public static void main(String[] args) {
        Phase nowPhase = Phase.Mark;
        String correctMark = decisionCorrectMark(MARKLIST);
        String correctNumber = decisionCorrectNumber(NUMBERLIST);
        System.out.println(correctMark + correctNumber);
        showFirstMessage();
        showMarkQuestionMessage();
        showMenuOfMark(MARKLIST);
        playGuessMark(nowPhase, correctMark);
        nowPhase = Phase.Number;
        showNumberQuestionMessage();
        playGuessNumber(nowPhase, correctNumber, correctMark);
        STDIN.close();
    }

    private static String receivePlayerSelect(Phase nowPhase) {
        int inputNumber = receiveinput();
        try {
            String selectTrumpElement = convertToTrumpElement(nowPhase, inputNumber);
            return selectTrumpElement;
        } catch (Exception e) {
            return receivePlayerSelect(nowPhase);
        }
    }

    private static void playGuessMark(Phase nowPhase, String correctMark) {
        showQuestionMessage();
        String selectMark = receivePlayerSelect(nowPhase);
        showMarkResultMessage(correctMark, selectMark);
        if (!isCorrect(selectMark, correctMark)) {
            playGuessMark(nowPhase, correctMark);
        }
    }

    private static void playGuessNumber(Phase nowPhase, String correctNumber, String correctMark) {
        showQuestionMessage();
        String selectNumber = receivePlayerSelect(nowPhase);
        showNumberResultMessage(correctMark, selectNumber, correctNumber);
        if (!isCorrect(selectNumber, correctNumber)) {
            playGuessNumber(nowPhase, correctNumber, correctMark);
        }
    }

    private static String decisionCorrectMark(List<String> markList) {
        int correctMarkInt = RANDOM.nextInt(markList.size());
        return markList.get(correctMarkInt);
    }

    private static String decisionCorrectNumber(List<String> numberList) {
        int correctNumberInt = RANDOM.nextInt(numberList.size());
        return numberList.get(correctNumberInt);
    }

    private static int receiveinput() {
        String inputStr;
        inputStr = STDIN.nextLine();
        if (!isNumber(inputStr)) {
            inputNumber = receiveinput();
            return inputNumber;
        }
        inputNumber = Integer.parseInt(inputStr);
        return inputNumber;
    }

    private static String convertToTrumpElement(Phase nowPhase, int inputNumber) {
        if (nowPhase == Phase.Mark) {
            return MARKLIST.get(inputNumber);
        } else {
            return NUMBERLIST.get(inputNumber + offSetSelectNumberInt);
        }
    }

    private static boolean isCorrect(String selectValue, String correctValue) {
        return selectValue.equals(correctValue);
    }

    private static boolean isNumber(String inputStr) {
        try {
            Integer.parseInt(inputStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void showMenuOfMark(List<String> markList) {
        int count = startcount;
        for (String mark : markList) {
            System.out.println(String.format("%s:%s", count, mark));
            count++;
        }
    }

    private static void showMarkResultMessage(String correctMark, String selectMark) {
        if (isCorrect(correctMark, selectMark)) {
            System.out.println(String.format("正解！図柄は%sだよ", correctMark));
            return;
        }
        System.out.println(String.format("残念！%sじゃないよ", selectMark));
    }

    private static void showNumberResultMessage(String correctMark, String selectNumber, String correctNumber) {
        if (isCorrect(correctNumber, selectNumber)) {
            System.out.println(String.format("正解！%sの%sだよ", correctMark, correctNumber));
            return;
        }
        System.out.println(String.format("残念！%sじゃないよ", selectNumber));
    }

    private static void showFirstMessage() {
        System.out.println(INTRODUCTION_MESSAGE);
    }

    private static void showQuestionMessage() {
        System.out.print(QUESTION_MESSAGE);
    }

    private static void showMarkQuestionMessage() {
        System.out.println(MARK_QUESTION_MESSAGE);
    }

    private static void showNumberQuestionMessage() {
        System.out.println(NUMBER_QUESTION_MESSAGE);
    }
}
