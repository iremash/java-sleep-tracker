public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        int beginIndex = 0;
        int endIndex = 0;
        String stringNumber = "";
        int intNumber = 0;
        for (int i = 0; i < args.length; i++) {
            String strings = args[i];
            int x = 0;
            while (x < strings.length()) {
                if (!Character.isWhitespace(strings.charAt(x))) {
                    beginIndex = x;
                    for (int y = x; y < strings.length(); y++) {
                        if (Character.isWhitespace(strings.charAt(y))) {
                            endIndex = y;
                            break;
                        } else {
                            endIndex = strings.length();

                        }
                    }
                    stringNumber = strings.substring(beginIndex, endIndex);
                    intNumber = Integer.parseInt(stringNumber);
                    sum += intNumber;
                    x = endIndex + 1;
                } else {
                    x++;
                }
            }
        }
        System.out.println(sum);
    }
}


