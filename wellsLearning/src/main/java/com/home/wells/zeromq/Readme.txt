ZeroMQ doesn't know anything about the data you send except its size in bytes
ZMQ use ZeroMQ Message Transport Protocol, and its sockets are not plain tcp sockets.

The ZeroMQ Message Transport Protocol (ZMTP) is a transport layer protocol for exchanging
messages between two peers over a connected transport layer such as TCP.
https://rfc.zeromq.org/spec:23/ZMTP/

socket types
socket-type = "REQ" | "REP"
            | "DEALER" | "ROUTER"
            | "PUB" | "XPUB"
            | "SUB" | "XSUB"
            | "PUSH" | "PULL"
            | "PAIR"

PGM (Pragmatic General Multicast) - PGM (Pragmatic General Multicast) is a protocol for
reliable multicast transport of data over IP networks.
// Connecting to the multicast address 239.192.1.1, port 5555,
// using the first Ethernet network interface on Linux
// and the Encapsulated PGM protocol
rc = zmq_connect(socket, "epgm://eth0;239.192.1.1:5555");
assert (rc == 0);
// Connecting to the multicast address 239.192.1.1, port 5555,
// using the network interface with the address 192.168.1.1
// and the standard PGM protocol
rc = zmq_connect(socket, "pgm://192.168.1.1;239.192.1.1:5555"); assert (rc == 0);