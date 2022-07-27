import java.util.Scanner;

public class Main {


        public static String[] calc(String input) {
            String[] calcInput = input.split(" ");
            if (calcInput.length != 3) {
                Scanner inputValueNext = new Scanner(System.in);
                System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");
                input = inputValueNext.nextLine();
                return calc(input);
            } else {
                return calcInput;
            }
        }


        public static void main(String[] args) {
            System.out.println("Введите арифмитеческую операцию, в таком формате a + b, a - b, a * b, a / b, числа от 1 до 10");
            Scanner inputValue = new Scanner(System.in);
            String input = inputValue.nextLine();
            int i;
            int result;
            String stringResult = "";
            while (!input.isEmpty()) {
                boolean arab;
                boolean arab1 = false;
                boolean arab2 = false;
                String[] calcInput;
                calcInput = calc(input);
                int value1 = 0;
                int value2 = 0;
                int num1=0;
                int num2=0;
                // Переводим в int. Если введены римские, выкинет исключение
                try {
                    value1 = Integer.parseInt(calcInput[0]);
                    if(value1>0 && value1<11){
                        arab1=true;}else { throw new IllegalStateException("Интервал ввода от 1 до 10");}
                } catch (NumberFormatException e) {
                    System.out.print("");
                }
                try {
                    value2 = Integer.parseInt(calcInput[2]);
                    if(value2>0 && value2<11){
                        arab2=true;}else { throw new IllegalStateException("Интервал ввода от 1 до 10");}
                } catch (NumberFormatException e) {
                    System.out.print("");
                }

                if (arab1 && arab2) {
                    arab=true;
                }else if(!arab1 && !arab2){
                    arab=false;
                }else {
                        throw new IllegalStateException("Введены и римские и арабские цифры!");
                }
                //считаем Арабские
                if (arab) {
                    result = switch (calcInput[1]) {
                        case "+" -> value1 + value2;
                        case "-" -> value1 - value2;
                        case "*" -> value1 * value2;
                        case "/" -> value1 / value2;
                        default ->
                                throw new IllegalStateException(calcInput[1] + " не является арифметической операцией");
                    };
                }
                else
                {//считаем римские
                    String[] romanLetters = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XV", "L", "LX", "LXX", "LXXX", "XC", "C"};
                    int[] arabLetters = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
                    if (calcInput.length != 3) {
                        throw new IllegalStateException("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");

                    }//переводим в арабские
                    for (i = 1; i <= 10; i++) {
                        String simbol1 = romanLetters[i];
                        String simbol2 = romanLetters[i];
                        if (simbol1.equals(calcInput[0])) {
                            value1 = arabLetters[i];
                            num1=1;
                        }
                        if (simbol2.equals(calcInput[2])) {
                            value2 = arabLetters[i];
                            num2=1;
                        }
                    }
                    if(num2==0){
                        throw new IllegalStateException("Интервал ввода от 1 до 10");
                    }
                    if(num1==0){
                        throw new IllegalStateException("Интервал ввода от 1 до 10");
                    }//считаем римские
                    switch (calcInput[1]) {
                        case "+":
                            result = value1 + value2;
                            break;
                        case "-":
                            result = value1 - value2;
                            if (result <= 0) {
                                throw new IllegalStateException("Введено не верное выражение, т.к. в римских цифрах нет ноля и отрицательных значений ");
                            }
                            break;
                        case "*":
                            result = value1 * value2;
                            break;
                        case "/":
                            if (value2 == 0) {
                                throw new IllegalStateException("Деление на ноль");
                            }else if (value2 <= 0 && value1 <= 0) {
                                    throw new IllegalStateException("Интервал ввода от 1 до 10");
                            }else {
                                result = value1 / value2;
                            }
                            break;
                        default:
                                throw new IllegalStateException(calcInput[1] + " не является арифметической операцией");
                    }//переводим абратно в арабские
                    int y = 19;
                    while (y > 0) {
                        int vihod = result - arabLetters[y];
                        if (vihod == 0) {
                            stringResult = romanLetters[y];break;
                        } else {
                            if (vihod > 0) {
                                i = 0;
                                while (vihod != i) {
                                    i++;
                                    stringResult = romanLetters[y] + romanLetters[i];
                                }
                                y = 0;
                            }
                        }
                        y--;
                    }
                }
                if (arab1 && arab2){System.out.println("Ответ = " + result);}else {
                System.out.println("Ответ " + stringResult);}
                System.out.print("Введите следующее выражение: ");stringResult="";
                input = inputValue.nextLine();
            }
            System.out.println("Вы вышли из калькулятора");
        }
    }
