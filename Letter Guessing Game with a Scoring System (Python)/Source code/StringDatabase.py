import random

class StringDatabase:
    @staticmethod
    def io():
        with open("four_letters.txt", "r") as file:
            content = file.read().split()
            currentW = random.choice(content)
        return currentW