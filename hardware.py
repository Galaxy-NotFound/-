import machine
import time
from machine import Pin, PWM
import dht
from machine import Pin, ADC
import random

# 模拟量
ps2_y = ADC(Pin(33))  # Pin33接A0
ps2_y.atten(ADC.ATTN_11DB)  # 这里配置测量量程为3.3V
pin2 = machine.Pin(2, machine.Pin.OUT)
pin5 = machine.Pin(5, machine.Pin.OUT)
p14 = Pin(14, Pin.IN)
# 数字量
p12 = Pin(12, Pin.IN)  # Pin12接D0
d = dht.DHT11(machine.Pin(13))
p27 = Pin(27, Pin.IN)
pin26 = machine.Pin(26, machine.Pin.OUT)
pin25 = machine.Pin(25, machine.Pin.OUT)


# 连接网络
def do_connect():
    import network
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    if not wlan.isconnected():
        print('connecting to network...')
        wlan.connect('Galaxy', '123456mm')
        while not wlan.isconnected():
            print("正在连接...")
            time.sleep(1)
    print('连接成功,当前的ip地址是:', wlan.ifconfig())


def glow():
    val_y = ps2_y.read()  # 模拟量范围0-4095。数字值越小，表示当前环境光的亮度越大，否则越小
    light = p12.value()  # 数字量0,1。数字0表示有光，1表示没有光
    print(val_y, light)
    if val_y < 2600 and light == 0:
        return ("光线充足。。。")
    else:
        return ("光线弱。。。")


def infrared():
    if (p14.irq(Pin.IRQ_RISING)):
        return ("当前有人员靠近")
    else:
        return ("当前范围内无人员靠近")


def defend():
    if (p27.value() == 0):
        return ("当前门锁未关")
    else:
        return ("当前门锁已关闭")


def main():
    import socket
    while (True):
        try:
            pin2 = machine.Pin(2, machine.Pin.OUT)
            do_connect()
            # 创建一个 TCP 套接字
            client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            # 服务器地址和端口
            server_address = ("49.233.184.192", 8888)
            # 发送消息
            print("开始连接服务器")
            client_socket.connect(server_address)
            print("连接成功")

            # 接收服务器回复
            reply = client_socket.recv(1024).decode('gbk')
            print("reply:", reply)
            print(type(reply))
            if (reply == "0"):
                print(0)
                pin2.value(0)
                message = "当前场景电灯已关闭"
            elif (reply == "1"):
                print(1)
                pin2.value(1)
                message = "当前场景电灯已打开"
            elif (reply == "2"):
                d.measure()
                print((d.humidity()))
                print(str(d.temperature()))
                message = str(d.humidity()) + "RH " + str(d.temperature()) + "℃"
            elif (reply == "3"):
                message = glow()
            elif (reply == "4"):
                message = infrared()
            elif (reply == "5"):
                message = defend()
            elif (reply == "6"):
                pin26.value(0)
                pin25.value(1)
                message = "当前场景风扇已打开"
            elif (reply == "7"):
                pin26.value(0)
                pin25.value(0)
                message = "当前场景风扇已关闭"
            elif (reply == "8"):
                num = random.randint(30, 80)
                message = str(num) + '%'
                # 关闭套接字

            client_socket.sendall(message.encode())
            client_socket.close()

        except Exception as e:
            print(f"An error occurred: {e}")


if __name__ == "__main__":
    main()
