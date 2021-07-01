package encryptdecrypt;

public class ShiftMethod implements EncodingMethod {
    @Override
    public void encode(Encoder encoder) {
        StringBuilder output = new StringBuilder();
        char[] inputArray = encoder.inputData.trim().toCharArray();
        for (char item : inputArray) {
            output.append((char) (item + encoder.key % 26));
        }
        encoder.outputData = output.toString();
    }
}
