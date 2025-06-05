# Java Peer-to-Peer Chat (P2P)

This is a basic peer-to-peer chat application written in Java using sockets. It allows two users to chat between two different computers (or on the same machine using different ports).

## 📁 Project Folder Structure

```

P2P/
├── Peer1.java
└── Peer1.class
└── Peer2.java
└── Peer2.class

````

## 💡 What It Does

- Peer1 and Peer2 both act as sender and receiver.
- Messages are exchanged directly using socket communication.
- Works on same device or over LAN (between two devices).

## ✅ How to Run

### Step 1: Compile

```bash
javac P2Pv2/Peer1.java
javac P2Pv2/Peer2.java
````

### Step 2: Start Peer1 (Device A)

```bash
java P2Pv2.Peer1 5000 6000 <DeviceB_IP>
```

* 5000: Port to receive messages
* 6000: Port to send to Peer2
* `<DeviceB_IP>`: IP address of Device B (Peer2)

### Step 3: Start Peer2 (Device B)

```bash
java P2Pv2.Peer2 6000 5000 <DeviceA_IP>
```

* 6000: Port to receive messages
* 5000: Port to send to Peer1
* `<DeviceA_IP>`: IP address of Device A (Peer1)

📝 To test on the same device, use `localhost` as the IP.

## 🔐 Notes

* Allow Java through your firewall when prompted.
* Make sure the selected ports are open on both devices.
* Type messages and press Enter to send.
* Type `exit` to quit.


