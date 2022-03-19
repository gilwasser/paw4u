package pawforyou.pawforyou.models;

public enum State {
    PROCCESS("שולם"),
    READY("נארז"),
    SHIPPED("נשלח");

    public final String label;


    private State(String label) {
        this.label = label;
    }
}
