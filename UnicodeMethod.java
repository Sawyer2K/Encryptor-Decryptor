package encryptdecrypt;

public class UnicodeMethod implements EncodingMethod {

    @Override
    public void encode(Encoder encoder) {
        StringBuilder output = new StringBuilder();
        char[] inputArray = encoder.inputData.trim().toCharArray();
        for (char item : inputArray) {
            output.append((char) (item + encoder.key));
        }
        encoder.outputData = output.toString();
    }
}
