package uso_threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BancoSincronizadoCondiciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco3 b = new Banco3();
		for(int i = 0; i < 100; i++) {
			EjecucionTransferencias3 r = new EjecucionTransferencias3(b, i, 2000);
			Thread t = new Thread(r);
			t.start();
		}
	}

}

class Banco3 {
	
	public Banco3() {
		cuentas = new double[100];
		
		for(int i = 0; i < cuentas.length; i++)
			cuentas[i] = 2000;
		
		saldoSuficiente = cierreBanco.newCondition();
	}
	
	public void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {
		// bloqueo el codigo para que sea ejecutado por un solo hilo de forma concurrente
		cierreBanco.lock();
		try {
			while(cuentas[cuentaOrigen] < cantidad)	// evalua que el saldo no es inferior a la transferencia
				saldoSuficiente.await();
				
			System.out.println(Thread.currentThread());
			cuentas[cuentaOrigen] -= cantidad;	// dinero que sale de la cuenta origen
			System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
			cuentas[cuentaDestino] += cantidad;
			System.out.printf("Saldo total: %10.2f\n", getSaldoTotal());	
			saldoSuficiente.signalAll();
		} finally {
			cierreBanco.unlock();
		}
	}
	
	public double getSaldoTotal() {
		double sumaCuentas = 0;
		for(double a: cuentas) {
			sumaCuentas += a;
		}
		return sumaCuentas;
	}
	
	private final double[] cuentas;
	private Lock cierreBanco = new ReentrantLock ();
	private Condition saldoSuficiente;
	
}

class EjecucionTransferencias3 implements Runnable {

	public EjecucionTransferencias3(Banco3 b, int de, double max) {
		banco = b;
		deLaCuenta = de;
		cantidadMax = max;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				int paraLaCuenta = (int)(100 * Math.random());
				double cantidad = cantidadMax * Math.random();
				banco.transferencia(deLaCuenta, paraLaCuenta, cantidad);
				Thread.sleep((int)(Math.random() * 10));
			}	
		} catch (InterruptedException e) {
			
		}
	}
	
	private Banco3 banco;
	private int deLaCuenta;
	private double cantidadMax;
	
}