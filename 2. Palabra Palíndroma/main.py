def is_palindrome_word(word):
    # Convertir a minusculas y quitar espacios
    limpia = ''
    for c in word:
        if c.isalnum():
            limpia += c.lower()

    # Comparar caracteres del principio y del final
    longitud = len(limpia)
    for i in range(longitud // 2):
        if limpia[i] != limpia[longitud - 1 - i]:
            return False
    return True

def main():
    word = input('Ingresa una palabra: ')

    if is_palindrome_word(word):
        print(f"'{word}' es una palabra palíndroma ✅.")
    else:
        print(f"'{word}' no es una palabra palíndroma ❌.")

if __name__ == "__main__":
    main()

# Ejemplos
# oso
# radar
# madam
# hello

