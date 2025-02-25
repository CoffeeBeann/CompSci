import sqlite3
con = sqlite3.connect("login.db")

s1 = input("id: ")
s2 = input("pwd: ")

query = f"select * from AuthInfo where id = '{s1}' and pwd = '{s2}'"
print(query)

cur = con.cursor()
res = cur.execute(query)
entry = res.fetchone()

if entry:
    print("Access granted")
else:
    print("Access denied")


