package timeline;

import static java.util.Arrays.asList;

public class OutputBuilder {
    private Output output = new Output();

    public static OutputBuilder anEmptyOutput() {
        return new OutputBuilder();
    }

    public Output create() {
        return output;
    }

    public OutputBuilder withLine(String line) {
        output.add(new Output(asList(line)));
        return this;
    }

}
