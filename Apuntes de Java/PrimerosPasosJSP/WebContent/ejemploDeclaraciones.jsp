<html>
	<body>
		<h1 style="text-aline:center">
			Ejemplo de scriptlets
		</h1>
		<%!
			// los modificadores de acceso private y public aca dan exactamente igual
			// los metodos son llamados desde una expresion
			
			private int resultado;
			
			public int metodoSuma(int num1, int num2) {
				resultado = num1 + num2;
				return resultado;
			}
			
			public int metodoResta(int num1, int num2) {
				resultado = num1 - num2;
				return resultado;
			}
			
			public int metodoMultiplica(int num1, int num2) {
				resultado = num1 * num2;
				return resultado;
			}
		%>
		El resultado de la suma es: <%= metodoSuma(7, 5) %>
		<br>
		El resultado de la resta es: <%= metodoResta(7, 5) %>
		<br>
		El resultado de la multiplicacion es: <%= metodoMultiplica(7, 5) %>
	</body>
</html>