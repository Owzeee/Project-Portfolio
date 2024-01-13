import sys
import os
from main import Guess

def start_game():
    print("Starting the guessing game...")
    key = input("press any key to start ")
    game = Guess()
    game.play_game()
