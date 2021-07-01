package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Encoder {

    private EncodingMethod method;

    final static String DEFAULT_mode = "enc";
    final static int DEFAULT_KEY = 0;
    final static String DEFAULT_INPUT_DATA = "";
    final static String DEFAULT_ALG = "shift";

    String mode = DEFAULT_mode;
    String alg = DEFAULT_ALG;
    int key = DEFAULT_KEY;
    String inputData = "";
    String outputData = DEFAULT_INPUT_DATA;
    String pathToInputFile = "";
    String pathToOutputFile = "";
    boolean inputFileExist = false;
    boolean outputFileExist = false;
    boolean inputFromConsole = false;

    protected void readVarargs(String[] inputArgs) {
        for (int i = 0; i < inputArgs.length - 2; i += 2) {
            switch (inputArgs[i]) {
                case "alg":
                    alg = inputArgs[i + 1];
                case "-key":
                    key = Integer.parseInt(inputArgs[i + 1]);
                    break;
                case "-mode":
                    mode = inputArgs[i + 1];
                    break;
                case "-data":
                    inputData = inputArgs[i + 1];
                    inputFromConsole = true;
                    break;
                case "-in":
                    pathToInputFile = inputArgs[i + 1];
                    inputFileExist = true;
                    break;
                case "-out":
                    pathToOutputFile = inputArgs[i + 1];
                    outputFileExist = true;
                    break;
            }
        }
    }

    protected void defineInputData() {
        if (inputFileExist) {
            try (Scanner sin = new Scanner(new File(pathToInputFile))) {
                while (sin.hasNext()) {
                    inputData = sin.nextLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (!inputFromConsole) {
            inputData = DEFAULT_INPUT_DATA;
        }
    }

    protected void defineMode() {
        switch (mode) {
            case "enc":
                break;
            case "dec":
                key = Math.negateExact(key);
                break;
        }
    }

    protected void processingInputData(Encoder encoder) {
        switch (alg) {
            case "shift":
                encoder.setMethod(new ShiftMethod());
                break;
            case "unicode":
                encoder.setMethod(new UnicodeMethod());
                break;
        }
    }

    protected void outputOfProcessedData() {
        if (outputFileExist) {
            try (PrintWriter writer = new PrintWriter(pathToOutputFile)) {
                writer.write(outputData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(outputData);
        }
    }

    protected void setMethod(EncodingMethod method) {
        this.method = method;
    }

    public void encode(Encoder encoder) {
        this.method.encode(encoder);
    }
}
