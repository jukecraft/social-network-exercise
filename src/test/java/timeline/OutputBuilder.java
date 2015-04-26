package timeline;

import static java.util.Arrays.asList;

public class OutputBuilder {
    private Output output = new Output();

    public static OutputBuilder anEmptyOutput() {
        return new OutputBuilder();
    }

    public static OutputBuilder anOutput() {
        return new OutputBuilder() //
            .withLine("");
    }

    public OutputBuilder withLine(String line) {
        output.add(new Output(asList(line)));
        return this;
    }

    public Output create() {
        return output;
    }

}
