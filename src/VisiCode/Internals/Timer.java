package VisiCode.Internals;

public class Timer {

    private double _lastLoopTime;

    public void Init() {
        _lastLoopTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float)(time - _lastLoopTime);
        _lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return _lastLoopTime;
    }
}