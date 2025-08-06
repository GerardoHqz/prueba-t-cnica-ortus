def is_palindrome_word(word):
    # Convertir a minusculas y quitar espacios
    clean_word = ''
    for c in word:
        if c.isalnum():
            clean_word += c.lower()

    # Comparar caracteres del principio y del final
    longitude = len(clean_word)
    for i in range(longitude // 2):
        if clean_word[i] != clean_word[longitude - 1 - i]:
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

