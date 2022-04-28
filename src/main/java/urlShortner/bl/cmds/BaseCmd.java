package urlShortner.bl.cmds;

public class BaseCmd<T> {
    private T       result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
