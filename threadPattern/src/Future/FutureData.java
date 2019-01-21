package Future;

public class FutureData implements Data {
    private RealData realData;
    private boolean ready = false;

    public synchronized void setContent(RealData realData) {
        if (ready) {
            // balk
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
