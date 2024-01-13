class Game:
    def __init__(self, word):
        self.word = word
        self.bad_guesses = 0
        self.missed_letters = 0
        self.score = 0

    def update_bad_guesses(self):
        self.bad_guesses += 1

    def update_missed_letters(self, missed):
        self.missed_letters += missed

    def update_score(self, score_change):
        self.score += score_change

    def update_status(self, status):
        self.status = status

    def get_final_game_data(self):
        return {
            'word': self.word,
            'status': self.status,
            'bad_guesses': self.bad_guesses,
            'missed_letters': self.missed_letters,
            'score': self.score
        }