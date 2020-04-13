package Server;

import University.UniversityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String [] args) throws RemoteException {
        UniversityRemoteObject universityRemoteObject = new UniversityRemoteObject();
        Registry registry = LocateRegistry.createRegistry(123);
        registry.rebind("University",universityRemoteObject);
    }
}
