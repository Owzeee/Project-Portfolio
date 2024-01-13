import os
from StringDatabase import StringDatabase
from Game import Game

class Guess:
    letter_frequency = {
        'a': 8.17, 'b': 1.49, 'c': 2.78, 'd': 4.25, 'e': 12.70,
        'f': 2.23, 'g': 2.02, 'h': 6.09, 'i': 6.97, 'j': 0.15,
        'k': 0.77, 'l': 4.03, 'm': 2.41, 'n': 6.75, 'o': 7.51,
        'p': 1.93, 'q': 0.10, 'r': 5.99, 's': 6.33, 't': 9.06,
        'u': 2.76, 'v': 0.98, 'w': 2.36, 'x': 0.15, 'y': 1.97, 
        'z': 0.07
    }

    def __init__(self):
        self.currentW = StringDatabase.io()
        self.currentG = ["-", "-", "-", "-"]
        self.letterG = []
        self.current_game = Game(self.currentW)  
        self.bad_guesses = 0
        self.missed_letters = 0
        self.score = 0
        self.game_statistics = []
        self.play_game()
        self.mode

    def main_menu_test(self):
        os.system('clear')
        print("++\n++ The great guessing game \n++\n")
        print("Current Word: " + self.currentW)
        print("Current Word: " + ''.join(self.currentG))
        print("Letters guessed: " + ' '.join(self.letterG) + "\n")
        print("g = guess, t = tell me, l for a letter, and q to quit\n")
    
    def main_menu_play(self):
        os.system('clear')
        print("++\n++ The great guessing game \n++\n")
        print("Current Word: " + ''.join(self.currentG))
        print("Letters guessed: " + ' '.join(self.letterG) + "\n")
        print("g = guess, t = tell me, l for a letter, and q to quit\n")
    
    def calculate_score(self):
        self.missed_letters = len([letter for letter in self.currentW if letter not in self.letterG])
        score_for_word = sum(self.letter_frequency[letter] for letter in self.currentW if letter not in self.letterG)
        score_for_word /= (len(self.letterG) if self.letterG else 1)
        score_for_word -= score_for_word * (0.10 * self.bad_guesses)
        return score_for_word
    
    def guess_letter(self, mode):
        while True:
            if self.mode == 'test':
                self.main_menu_test()
            else:
                self.main_menu_play()

            optionL = input("Enter a letter or 'back' to go back: ")
            if optionL == 'back':
                break

            if len(optionL) != 1 or not optionL.isalpha():
                print("Please enter a single letter.")
                key = input("Press any key to continue... ")
                continue

            if optionL in self.letterG:
                print("You already guessed that letter.")
                key = input("Press any key to continue... ")
                continue

            if optionL in self.currentW:
                letter_found = False
                for i in range(len(self.currentW)):
                    if self.currentW[i] == optionL:
                        self.currentG[i] = optionL
                        letter_found = True
                if letter_found:
                    print("@@\n@@ FEEDBACK: Woo hoo, you found a letter!\n@@")
                    key = input("Press any key to continue... ")
                    if '-' not in self.currentG:
                        print("FEEDBACK: Damn, you really are SMART!")
                        break
            else:
                if optionL not in self.letterG:
                    self.letterG.append(optionL)
                    self.current_game.update_missed_letters(1)
                    print("@@\n@@ FEEDBACK: Wrong letter! Try again!\n@@")
                    key = input("Press any key to continue... ")

    def finalize_word(self, gave_up=False):
        if gave_up:
            self.current_game.update_score(-sum(self.letter_frequency[letter] for letter in self.currentW))
            self.current_game.update_status('Gave up')
        else:
            self.current_game.update_score(self.calculate_score())
            self.current_game.update_status('Success')

        self.game_statistics.append(self.current_game.get_final_game_data())

        self.currentW = StringDatabase.io()
        self.currentG = ["-", "-", "-", "-"]
        self.letterG = []
        self.bad_guesses = 0
        self.missed_letters = 0
        self.score = 0
        self.current_game = Game(self.currentW)

    def print_final_report(self):
        final_score = sum(game['score'] for game in self.game_statistics)
        os.system('clear')
        print("\n++\n++ Game Report\n++\n")
        print(f"{'Game':<5} {'Word':<5} {'Status':<10} {'Bad Guesses':<12} {'Missed Letters':<15} {'Score':<5}")
        print("----  ----  ------     -----------  --------------  -----")
    
        for i, game in enumerate(self.game_statistics, 1):
            print(f"{i:<5} {game['word']:<5} {game['status']:<10} {game['bad_guesses']:<12} {game['missed_letters']:<15} {game['score']:<5.2f}")

        print(f"\nFinal Score: {final_score:.2f}\n")
    
    def play_game(self):
        while True:
            self.mode = input("please, enter 'test' for test mode or 'play' for play mode: ").lower()
            if self.mode in ['test', 'play']:
                break
            print("Invalid input.")

        while True:
            if self.mode == 'test':
                self.main_menu_test()
            else:
                self.main_menu_play()
            option = input("Enter Option: ")

            if option == 'g':
                guess = input("Make your guess: ")
                if guess == self.currentW:
                    print("FEEDBACK: Correct guess! Well done!")
                    self.finalize_word()
                    self.currentW = StringDatabase.io()
                    self.currentG = ["-", "-", "-", "-"]
                    self.letterG = []
                    self.current_game = Game(self.currentW)
                    key = input("Press any key to continue... ")
                else:
                    self.current_game.update_bad_guesses()
                    print("\nFEEDBACK: Incorrect guess! Try again!")
                    key = input("Press any key to continue... ")

            elif option == 't':
                print("You really should have guessed this: " + self.currentW)
                self.finalize_word(gave_up = True)
                self.currentW = StringDatabase.io()
                self.currentG = ["-", "-", "-", "-"]
                self.letterG = []
                self.current_game = Game(self.currentW)
                key = input("Press any key to continue... ")

            elif option == 'l':
                self.guess_letter(self.mode)

            elif option == 'q':
                self.print_final_report()
                print("Closing the program")
                break

            else:
                print("Invalid Input, try again: ")
                key = input("Press any key to continue... ")


game = Guess()
