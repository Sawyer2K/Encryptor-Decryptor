package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        encoder.readVarargs(args);
        encoder.defineInputData();
        encoder.defineMode();
        encoder.processingInputData(encoder);
        encoder.encode(encoder);
        encoder.outputOfProcessedData();
    }
}