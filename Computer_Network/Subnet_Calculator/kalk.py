import os
import socket
import subprocess
import sys


def check_ip(ip):
    mask = get_ip_mask(ip)
    if not mask.isdigit():
        print("Wrong symbols in mask: {}".format(mask))
        return False
    if int(mask) > 32 or int(mask) < 0:
        print("Wrong number in mask: {}".format(mask))
        return False
    tab_ip = get_ip(ip)
    tab_ip = tab_ip.split(".")
    if len(tab_ip) != 4:
        print("Wrong number of segments in ip: {}".format(len(tab_ip)))
        return False
    for i in range(len(tab_ip)):
        if not (tab_ip[i].isdigit() or tab_ip[i] == "."):
            print("Wrong symbols in segment {} in ip: {}".format(i + 1, tab_ip[i]))
            return False
        if int(tab_ip[i]) > 255 or int(tab_ip[i]) < 0:
            print("Wrong number in segment {} in ip:, number={}".format(i + 1, tab_ip[i]))
            return False
    print("ip is ok: " + ip)
    return True


def net_address(ip):
    ip_bin = dec_to_bin(get_ip(ip)).split(".")
    mask = ip_mask_to_bin(ip).split(".")
    result = ""
    for segment in range(4):
        for bit in range(8):
            if ip_bin[segment][bit] == '1' and mask[segment][bit] == '1':
                result += "1"
            else:
                result += "0"
        if segment != 3:
            result += "."
    return result


def broadcast(ip):
    ip_bin = dec_to_bin(get_ip(ip)).split(".")
    mask = ip_mask_to_bin(ip).split(".")
    result = ""
    for segment in range(4):
        for bit in range(8):
            if ip_bin[segment][bit] == '1' and mask[segment][bit] == '1':
                result += "1"
            elif mask[segment][bit] == '0':
                result += "1"
            else:
                result += "0"
        if segment != 3:
            result += "."
    return result


def dec_to_bin(ip):
    segments = ip.split(".")
    tab = []
    if len(segments) != 4:
        return "Wrong number of segments {}".format(len(segments))
    for number in segments:
        tab.append(bin(int(number)).replace("0b", ""))
    result = ""
    for nr_seg in range(4):

        if len(tab[nr_seg]) > 8:
            return "Too large number in segment nr:{}".format(nr_seg)
        i = 0
        while len(tab[nr_seg]) + i < 8:
            result += '0'
            i += 1

        result += tab[nr_seg]
        if nr_seg != 3:
            result += "."
    return result


def bin_to_dec(ip):
    z = ip.split(".")
    tab = []
    if len(z) != 4:
        return "Wrong number of segments {}".format(len(z))
    for xc in range(4):
        tab.append((int(z[xc])))
    result = ""

    for xx in range(4):
        decimal, i, n = 0, 0, 0
        while tab[xx] != 0:
            dec = tab[xx] % 10
            decimal = decimal + dec * pow(2, i)
            tab[xx] = tab[xx] // 10
            i += 1
        result += str(decimal)
        if xx != 3:
            result += "."
    return result


def ip_mask_to_bin(ip):
    x = int(get_ip_mask(ip))
    result = ""
    for c in range(4):
        for d in range(8):
            if x > 0:
                result += "1"
            else:
                result += "0"
            x = x - 1
        if c != 3:
            result += "."
    return result


def get_ip(ip):
    x = ip[:ip.find('/')]
    return x


def get_ip_mask(ip):
    x = ip[ip.find('/') + 1:]
    return x


def net_class(ip):
    first = ip[:ip.find('.')]
    if int(first) < 128:
        return "A (very big)"
    elif int(first) < 192:
        return "B (medium size)"
    elif int(first) < 224:
        return "C (small)"
    elif int(first) < 240:
        return "D (for group transmission)"
    else:
        return "E (reserved for IETF)"


def get_mask_from_system():
    ip = socket.gethostbyname((socket.gethostname()))
    proc = subprocess.Popen('ipconfig', stdout=subprocess.PIPE)
    while True:
        line = proc.stdout.readline()
        if str(ip).encode() in line:
            break
    mask = str(proc.stdout.readline()).rstrip().split(":")[-1].replace(' ', '')
    return mask[:-5]


def amount_of_hosts(ip):
    mask = int(get_ip_mask(ip))
    return 2 ** (32 - mask) - 2


def first_host_address(ip):
    address = bin_to_dec(net_address(ip))
    first_host = address[:address.rfind('.') + 1] + str(int(address[address.rfind('.') + 1:]) + 1)
    return first_host


def last_host_address(ip):
    broad = bin_to_dec(broadcast(ip))
    last_host = broad[:broad.rfind('.') + 1] + str(int(broad[broad.rfind('.') + 1:]) - 1)
    return last_host


def mask_to_int(mask_bin):
    z = mask_bin.split(".")
    result = 0

    for xc in range(4):
        for xx in range(8):
            if z[xc][xx] == "1":
                result += 1

    return result


def private_or_public(ip):
    tabip = get_ip(ip)
    tabip = tabip.split(".")
    if tabip[0] == "10":
        return "address {} is private".format(ip)
    if tabip[0] == "172" and 16 <= int(tabip[1]) <= 31:
        return "address {} is private".format(ip)
    if tabip[0] == "192" and tabip[1] == "168":
        return "address {} is private".format(ip)
    return "address {} is public".format(ip)


def prints(ip):
    result = ip + "\n"
    mask_bin = ip_mask_to_bin(ip)
    ip_dec = get_ip(ip)
    net_address_bin = net_address(ip)
    broadcast_bin = broadcast(ip)

    result = result + "mask:\n"
    result = result + mask_bin + "\n"
    result = result + bin_to_dec(mask_bin) + "\n"
    result = result + str(mask_to_int(mask_bin)) + "\n"

    result = result + "ip:\n"
    result = result + dec_to_bin(ip_dec) + "\n"
    result = result + ip_dec + "\n"

    result = result + "Net address:\n"
    result = result + net_address_bin + "\n"
    result = result + bin_to_dec(net_address_bin) + "\n"

    result = result + "Class:\n"
    result = result + net_class(ip_dec) + "\n"

    result = result + "broadcast:\n"
    result = result + broadcast_bin + "\n"
    result = result + bin_to_dec(broadcast_bin) + "\n"

    result = result + "Amount of hosts:\n"
    result = result + str(amount_of_hosts(ip)) + "\n"

    result = result + "host min:\n"
    result = result + first_host_address(ip) + "\n"

    result = result + "host max:\n"
    result = result + last_host_address(ip) + "\n"

    result = result + "private or public:\n"
    result = result + private_or_public(ip) + "\n"
    return result


def main():
    if len(sys.argv) == 1:
        ip = socket.gethostbyname(socket.gethostname())
        ip = str(ip)
        ip = ip + "/" + str(mask_to_int(dec_to_bin(get_mask_from_system())))
    else:
        x = sys.argv[1]
        ip = x

    if check_ip(ip):
        output = prints(ip)
        plik = open('subnet_info.txt', 'w+')
        plik.write(output)
        plik.close()
        if len(sys.argv) > 1:
            response = os.system("ping  " + ip[:ip.find('/')])
            if response == 0:
                print(ip[:ip.find('/')] + " is up!")
            else:
                print(ip[:ip.find('/')] + " is down!")


if __name__ == "__main__":
    main()
