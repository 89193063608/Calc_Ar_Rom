import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Примечание! При вводе Римского 0, необходимо ввести английскую букву О!");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Введите Первое число, затем Знак арифметического действия, Второе число. Нажмите Enter!:");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws Exception {
        int num_1;
        int num_2;
        String op;
        String result;
        boolean isRoman;

        String[] operands = expression.split("[+\\-*/]");

        if (operands.length != 2) throw new Exception("Ввести необходимо только два числа");

        op = detectOperation(expression);

        if (op == null) throw new Exception("Неизвестный знак действия!");

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {

            num_1 = Roman.convertToArabian(operands[0]);
            num_2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }

        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num_1 = Integer.parseInt(operands[0]);
            num_2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }

        else {
            throw new Exception("Числа должны быть или только Арабские, или только Римские! Не торопитесь!");
        }

        if (num_1 > 10 || num_2 > 10) {
            throw new Exception("Соблюдайте диапазон от 0 до 10!");
        }

        int arabian = calc(num_1, num_2, op);

        if (isRoman) {

            if (arabian <= 0) {

                throw new Exception("Римское число не может быть отрицательным!");
            }

            result = Roman.convertToRoman(arabian);
        } else {

            result = String.valueOf(arabian);
        }

        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String op) {

        if (op.equals("+")) return a + b;
        else if (op.equals("-")) return a - b;
        else if (op.equals("*")) return a * b;
        else return a / b;
    }
    class Roman {
        static String[] romanArray = new String[]{"O", "I", "II", "III", "IV", "V", "VI",
                "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
                "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV",
                "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV",
                "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII",
                "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
                "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
                "XCVII", "XCVIII", "XCIX", "C"};
        public static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }
        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }
        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }
    }
}
