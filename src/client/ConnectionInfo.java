package client;

class ConnectionInfo
{
    private static class SingletonHelper {
        private static final ConnectionInfo instance = new ConnectionInfo();
    }

    private boolean isConnected;

    static ConnectionInfo getInstance()
    {
        return SingletonHelper.instance;
    }

    private ConnectionInfo()
    {
        isConnected = true;
    }

    synchronized boolean isConnected()
    {
        return isConnected;
    }

    synchronized void disconnect()
    {
        isConnected = false;
    }
}
