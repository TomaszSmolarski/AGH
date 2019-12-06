import pygame
import os

letterX = pygame.image.load(os.path.join('res', 'letterX.png'))
letterO = pygame.image.load(os.path.join('res', 'letterO.png'))


class Grid:
    pygame.init()

    def __init__(self):
        self.grid_lines = [((0, 200), (600, 200)),  # first horizontal line
                           ((0, 400), (600, 400)),  # second horizontal line
                           ((200, 0), (200, 600)),  # first vertical line
                           ((400, 0), (400, 600))]  # second vertical line

        self.grid = [[0 for x in range(3)] for y in range(3)]
        # search directions  N         NW        W       SW       S       SE      E       NE
        self.search_dirs = [(0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]
        self.game_over = False
        self.x_wins=0
        self.o_wins=0
        self.who_won=""

    def draw(self, surface):
        font = pygame.font.SysFont("comicsansms", 50)
        x_text = font.render("X=" + str(self.x_wins), True, (0, 128, 0))
        o_text = font.render("O=" + str(self.o_wins), True, (128, 0, 0))
        won_text = font.render(str(self.who_won), True, (200, 200, 200))
        surface.blit(x_text, (50 - x_text.get_width() / 2, 600))
        surface.blit(o_text, (550 - o_text.get_width() / 2, 600))
        surface.blit(won_text, (300 - won_text.get_width() / 2, 600))
        for line in self.grid_lines:
            pygame.draw.line(surface, (200, 200, 200), line[0], line[1], 2)


        for y in range(len(self.grid)):
            for x in range(len(self.grid[y])):
                if self.get_cell_value(x, y) == "X":
                    surface.blit(letterX, (x * 200, y * 200))
                elif self.get_cell_value(x, y) == "O":
                    surface.blit(letterO, (x * 200, y * 200))

    def get_cell_value(self, x, y):
        return self.grid[y][x]

    def set_cell_value(self, x, y, value):
        self.grid[y][x] = value

    def get_mouse(self, x, y, player):
        if self.get_cell_value(x, y) == 0:
            self.set_cell_value(x, y, player)
            self.check_grid(x, y, player)


    def is_within_bounds(self, x, y):
        return x >= 0 and x < 3 and y >= 0 and y < 3

    def check_grid(self, x, y, player):
        count = 1
        self.who_won=0
        for index, (dirx, diry) in enumerate(self.search_dirs):
            if self.is_within_bounds(x + dirx, y + diry) and self.get_cell_value(x + dirx, y + diry) == player:
                count += 1
                xx = x + dirx
                yy = y + diry
                if self.is_within_bounds(xx + dirx, yy + diry) and self.get_cell_value(xx + dirx, yy + diry) == player:
                    count += 1
                    if count == 3:
                        break
                if count < 3:
                    new_dir = 0
                    # mapping the indices to opposite direction: 0-4 1-5 2-6 3-7 4-0 5-1 6-2 7-3
                    if index == 0:
                        new_dir = self.search_dirs[4]  # N to S
                    elif index == 1:
                        new_dir = self.search_dirs[5]  # NW to SE
                    elif index == 2:
                        new_dir = self.search_dirs[6]  # W to E
                    elif index == 3:
                        new_dir = self.search_dirs[7]  # SW to NE
                    elif index == 4:
                        new_dir = self.search_dirs[0]  # S to N
                    elif index == 5:
                        new_dir = self.search_dirs[1]  # SE to NW
                    elif index == 6:
                        new_dir = self.search_dirs[2]  # E to W
                    elif index == 7:
                        new_dir = self.search_dirs[3]  # NE to SW

                    if self.is_within_bounds(x + new_dir[0], y + new_dir[1]) \
                            and self.get_cell_value(x + new_dir[0], y + new_dir[1]) == player:
                        count += 1
                        if count == 3:
                            break
                    else:
                        count = 1

        if count == 3:
            if player=='X':
                self.x_wins+=1
                self.who_won="X WON"

            elif player=='O':
                self.o_wins+=1
                self.who_won="O WON"
            self.game_over = True
        else:
            self.game_over = self.is_grid_full()

    def is_grid_full(self):
        for row in self.grid:
            for value in row:
                if value == 0:
                    return False
        self.who_won="DRAW"
        return True

    def clear_grid(self):
        for y in range(len(self.grid)):
            for x in range(len(self.grid[y])):
                self.set_cell_value(x, y, 0)

    def print_grid(self):
        for row in self.grid:
            print(row)
