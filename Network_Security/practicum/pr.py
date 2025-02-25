#!bin/usr/python3

def showpkts_ARP(data):
    data_size = []
    header = 0
    offset = []
    for k in range(24,len(data)):
        if header == 8: #get size
            data_size.append(data[k] + data[k+1]*256 + data[k+2]*65536 + data[k+3]*16777216)
            offset.append(k+8)
            header = -(data[k] + data[k+1]*256 + data[k+2]*65536 + data[k+3]*16777216)- 8
            #subtract an extra 8 because we're still reading in the data size
        header += 1          


    for i in range(len(data_size)):
        counter = 1
        dst_mac = data[offset[i]:6+offset[i]]
        src_mac = data[6+offset[i]: 12+offset[i]]
        arp_code = data[12+offset[i]: 14+offset[i]] # arp = 0806
        sndr_HW_addr = src_mac
        opcode = data[19+offset[i]:20+offset[i]]
        sndr_prot_addr = data[27+offset[i]:30+offset[i]]
        trg_hw_addr = data[30+offset[i]:36+offset[i]]
        trg_ip = data[36+offset[i]: 40+offset[i]]

        if (arp_code == b'\x08\x06'):
            print("===")
            print("Dst-MAC= " + str(dst_mac), end="")
            print("Src-MAC= " + str(src_mac), end="")
            print("Opcode = \n", )
            
            print("Sender-HW-Addr= " + str(sndr_HW_addr), end="")
            print("Target-HW-Addr= " + str(trg_hw_addr), end = "")
            print("Target-Prot-Addr + " + str(trg_ip), end = "")
            print("===")
