import math

def is_prime_number(numberber):
    # Los números menores que 2 no son primos
    if numberber < 2:
        return False

    # El 2 es el único número par que es primo
    if numberber == 2:
        return True

    # Si es un número par mayor que 2, no puede ser primo
    if numberber % 2 == 0:
        return False

    # Revisamos solo los impares desde 3 hasta la raíz cuadrada del número
    for i in range(3, int(math.sqrt(numberber)) + 1, 2):
        if numberber % i == 0:
            return False

    return True

def main():
    try:
        number = int(input("Ingrese un número entero: "))
        if number < 0:
            print("Por favor ingrese un número positivo.")
        else:
            if is_prime_number(number):
                print(f"{number}, si es primo ✅.")
            else:
                print(f"{number}, no es primo ❌.")
    except ValueError:
        print("Error: Por favor ingrese un número entero positivo válido.")

if __name__ == "__main__":
    main()
