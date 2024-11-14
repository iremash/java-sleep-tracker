public class SumFloatPunct {
    public static void main(String[] args) {
        float sum = 0;
        int beginIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < args.length; i++) {
            String strings = args[i];
            for (int x = 0; x < strings.length(); x++) {
                if (!Character.isWhitespace(strings.charAt(x))
                        && Character.START_PUNCTUATION != Character.getType(strings.charAt(x))
                        && Character.END_PUNCTUATION != Character.getType(strings.charAt(x))) {
                    beginIndex = x;
                    for (int y = x; y < strings.length(); y++) {
                        if (Character.isWhitespace(strings.charAt(y))
                                || Character.START_PUNCTUATION == Character.getType(strings.charAt(y))
                                || Character.END_PUNCTUATION == Character.getType(strings.charAt(y)) ){
                            endIndex = y;
                            break;
                        } else {
                            endIndex = strings.length();

                        }
                    }
                    sum += Float.parseFloat(strings.substring(beginIndex, endIndex));
                    x = endIndex;
                }
            }
        }
        System.out.println(sum);
    }
}

