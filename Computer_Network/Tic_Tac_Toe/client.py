import pygame
from grid import Grid
import sys

import socket
surface = pygame.display.set_mode((600, 700))
pygame.display.set_caption('Tic-tac-toe')

# create a separate thread to send and receive data from the server
import threading

def create_thread(target, arguments):
    thread = threading.Thread(target=target, args=arguments)
    thread.daemon = True
    thread.start()

def receive_data(socketObj, letter):
    global turn
    while True:
        data = socketObj.recv(1024).decode()  # receive data from the client, it is a blocking method
        data = data.split('-')  # the format of the data after splitting is: ['x', 'y', 'yourturn', 'playing']
        x, y = int(data[0]), int(data[1])
        if data[2] == 'yourturn':
            turn = True
        if data[3] == 'False':
            grid.game_over = True
        grid.x_wins=int(data[4])
        grid.o_wins=int(data[5])
        grid.who_won = data[6]
        if grid.get_cell_value(x, y) == 0:
            grid.set_cell_value(x, y, letter)


if len(sys.argv) == 1:
    HOST=socket.gethostname()
else:
    HOST=sys.argv[1]
PORT = 65432

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    #try to connect
    sock.connect((HOST, PORT))
except:
    print("server not exist, making new")
    #make server
    connection_established = False
    conn, addr = None, None

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.bind((HOST, PORT))

    sock.listen(5)
    print("server listening... ip="+str(sock.getsockname()))

    def waiting_for_connection():
        global connection_established, conn, addr
        conn, addr = sock.accept()  # wait for a connection, it is a blocking method
        print('client is connected: '+str(addr))
        connection_established = True
        receive_data(conn, 'O')


    # run the blocking functions in a separate thread
    create_thread(waiting_for_connection, [])
    grid = Grid()
    running = True
    player = "X"
    turn = True
    playing = 'True'

#running on conn
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            if event.type == pygame.MOUSEBUTTONDOWN and connection_established:
                if pygame.mouse.get_pressed()[0]:
                    if turn and not grid.game_over:
                        pos = pygame.mouse.get_pos()
                        cellX, cellY = pos[0] // 200, pos[1] // 200
                        grid.get_mouse(cellX, cellY, player)
                        if grid.game_over:
                            playing = 'False'
                        send_data = '{}-{}-{}-{}-{}-{}-{}'.format(cellX, cellY, 'yourturn', playing,grid.x_wins,grid.o_wins,grid.who_won).encode()
                        conn.send(send_data)
                        turn = False

            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE and grid.game_over:
                    grid.clear_grid()
                    grid.who_won = ""
                    grid.game_over = False
                    playing = 'True'
                elif event.key == pygame.K_ESCAPE:
                    running = False

        surface.fill((0, 0, 0))
        grid.draw(surface)

        pygame.display.flip()


#####################client
#receive data sock letter X

# run the blocking functions in a separate thread)
create_thread(receive_data, [sock, 'X'])

grid = Grid()
running = True
player = "O"
turn = False
playing = 'True'


#running on sock
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.MOUSEBUTTONDOWN and not grid.game_over:
            if pygame.mouse.get_pressed()[0]:
                if turn and not grid.game_over:
                    pos = pygame.mouse.get_pos()
                    cellX, cellY = pos[0] // 200, pos[1] // 200
                    grid.get_mouse(cellX, cellY, player)
                    if grid.game_over:
                        playing = 'False'
                    send_data = '{}-{}-{}-{}-{}-{}-{}'.format(cellX, cellY, 'yourturn', playing,grid.x_wins,grid.o_wins,grid.who_won).encode()
                    sock.send(send_data)
                    turn = False

        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE and grid.game_over:
                grid.clear_grid()
                grid.who_won=""
                grid.game_over = False
                playing = 'True'
            elif event.key == pygame.K_ESCAPE:
                running = False

    surface.fill((0, 0, 0))
    grid.draw(surface)

    pygame.display.flip()
