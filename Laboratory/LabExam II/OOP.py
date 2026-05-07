# OOP
from datetime import datetime

# 1.1: ถ้านักกีฬาอายุมากกว่า 30 ขึ้นไป ให้ return ค.ศ. เกิด ถ้าตั้งแต่ 30 ลงมา returns ปี ค.ศ. ที่จะ 50 ปี
class Atheles:
    def __init__(self, name: str, age: int, /) -> None:
        self.name = name
        self.age = age

        self.current_year = datetime.now().year

    def caculate_year(self, /) -> int:
        if (self.age < 30):
            return self.current_year - self.age
        else:
            return (50 - self.age) + self.current_year
keen = Atheles("Keen", 19)
kate = Atheles("Kate", 31)
por = Atheles("Por", 30)
ice = Atheles("Ice", 1)
print(keen.caculate_year(), kate.caculate_year(), por.caculate_year(), ice.caculate_year(), sep=',\n')


# 1.2: ม้างงง~
# เพิ่มนักกีฬา และ ลบนักกีฬา~
class Team:
    def __init__(self, sports_team: str, /):
        self.sports_team = sports_team
        self.atheles: list[Atheles] = []

    def add_atheles(self, athele: Atheles, /):
        self.atheles.append(athele)

    def delete_atheles(self, name: str, /):
        atheles = self.atheles

        for i in range(len(atheles)):
            if atheles[i].name == name:
                del atheles[i]
                break
        # self.atheles = atheles
        return atheles