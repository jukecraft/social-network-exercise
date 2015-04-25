package timeline;

import java.util.ArrayList;
import java.util.List;

import commons.SocialNetworkingValueObject;

public class Output extends SocialNetworkingValueObject {

    private List<String> output;

    public Output(List<String> output) {
        this.output = output;
    }

    public Output() {
        output = new ArrayList<>();
    }

    public void add(Output output) {
        this.output.addAll(output.output);
    }

    public List<String> getOutput() {
        return output;
    }

}
