package afedorov.entities;

public class ValueOfParameter {

    private Parameter parameter;
    private String value;

    public ValueOfParameter(Parameter parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
