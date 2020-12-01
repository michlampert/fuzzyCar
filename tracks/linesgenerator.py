import re

f = open("tracks/track1.svg")
lines = f.readlines()

count = 0

for line in lines:
    if re.compile(r'^ *d=".*" *$').search(line):
        count += 1

out = open("tracks/track1.txt", "w+")
out.write(str(count)+"\n")

last_x = 0
last_y = 0

for line in lines:
    line = line.strip()
    if re.compile(r'^ *d=".*" *$').search(line):
        x1, y1, x2, y2 = [float(num) for num in re.split(" |,", line[5:-1])]

        if line[3] == "m":
            x2 += last_x
            y2 += last_y
        
        out.write(str(x1*3.78) + "\n")
        out.write(str(y1*3.78) + "\n")
        out.write(str(x2*3.78) + "\n")
        out.write(str(y2*3.78) + "\n")

        last_x = x2
        last_y = y2

# for line in lines:
#     line = line.strip()
#     if re.compile(r'^ *d=".*" *$').search(line):
#         nums = [float(num) for num in re.split(r" |,|\\L", line[5:-1])]

#         pairs = list(zip(nums[::2], nums[1::2]))

#         for [[x1,y1],[x2,y2]] in list(zip(pairs, pairs[1:] + [pairs[0]])):
#             out.write(str(x1) + "\n")
#             out.write(str(y1) + "\n")

#             out.write(str(x2) + "\n")
#             out.write(str(y2) + "\n")
