# สรุป OOP แบบง่ายๆ~ (มั้ง~) IC
ใน Part นี้คือ SuperClass, SubClass หรือ ChildClass ในเรื่อง **Inheritance Class** หรือ **Inherit Class**
```py
class Shape:<--- (0)
    def __init__(self,):
        pass

class Circle(Shape): <--- (1)
    def __init__(self,):
        pass
```

**Inheritance Class** หรือแปลง่ายๆคือ Class "is-a" หรือเป็นการบ่งบอกว่า Class นั้นๆคือ**เป็น** Object ของอีก Object อีก เราจะเรียน Class แบบนี้ว่า `Inherit Class`
เช่นในัวอย่างนี้เป็น `Shape` กับ `Circle` ซึ่ง `Circle` **คือสิ่งหนึ่งใน** `Shape` ใช่มะ~

ทีนี้เราจะมาแยกกันว่าใน Inheritance Class` นี้อันไหนเป็น SuperClass และ SubClass 

**(0)** ส่วนนี้เป็น `Class Object` หรือ `BluePrint` Shape เฉยๆ

**(1)** ส่วนนี้จะนับเป็น **ChildClass** หรือ **SubClass** เนื่องจากเราเอา class `Circle` เข้าไปใน class `Shape`
- ดังนั้น class `Shape` จะถูกนับเป็น **SuperClass** หรือ **ParentClass** ของ `Circle` และ class `Circle` จะเป็น **SubClass** หรือ **ChildClass** ของ class `Shape`

จบ~

ทีนี้มันจะมี function `super()` และ `ABC` หรือ `Abstruct-Base Class` Library ใน Python

## SuperClass และ SubClass
เอา `super` function ก่อน~ 
คำสั่ง `super()` คือ Function นะ~
ณ ที่นี้เราจะเขียนแบบนี้
```py
from typing import Self, Any

class Shape:
    def __init__(self: Self, radius: int, /):
        self.r = radius <--- (2)
    [...]
class Circle(Shape):
    def __init__(self: Self, radius: int, /):
        super()__init__(radius) <--- (1)
    [...]

Circle(5) <--- (0)
```
ที่นี้เราจะมาแตกสิ่งที่้ราเห็นใน CodeBlock นี้กัน~

**(0)** ส่วนแรก แค่นำเลข $5$ เข้าไปใน `Circle` ผ่าน parameter ที่ชื่อว่า `radius` ของ `Circle`~

**(1)** ส่วนนี้จะเป็น `super()` function ละ~ ค่อยๆดูนะ เราจำได้ใช่มั้นว่า `SuperClass` ของ `Circle` คืออะไร?

<f>[TextBox] <btn>Check</btn></f>
<!-- TODO: make user answer it and press check it check the answer If 'Answer' is 'Shape' is correct. and can passing to the next page below.-->

- **SuperClass** ของ `Circle` เป็น `Shape` ที่นี้ใน `super()` function จะเป็นการเรียกใช้งาน **SuperClass** ผ่าน `super()` function และ ร ที่นี้เราเรียกใช้ `__init__()` method ของ `super()`

> นั้นหมายความว่า จาก Class Circle ที่เราใส่ arguments radius เป็น 5 ผ่าน Circle Class เข้าไป ใน Class Circle มีการเรียกใช้งาน `super()` function ของ `Circle` คือ `Shape` แล้วเราเรียกใช้งาน `constructor` คือ `__init__()` method แล้วใส่ radius เข้าไป
- แปลว่า `self.r` ใน class `Shape` จะเท่ากับ $5$

จบแค่นี้แหละ~ SuperClass และ SubClass~

## ABC หรือ Abstruct-Base Class
ต่อไปจะเป็น `ABC` หรือ `Abstruct-Base Class` อธิบายสั้นๆจะประมาณว่า **Abstruct-Base Class** คือการที่เราบอกว่า Class นั้นๆ~ **จำเป็นต้อง**เรียกใช้งาน Class นั้นจาก Class ลูกของมันเองเท่านั้น หรือ `SubClass` เท่านั้น~

วิธีการสร้างเราจำเป็ยต้องนำ library `ABC` เข้ามาใช้งาน และต้องใช้ `Decorators` ด้วยย~
ณ ที่นี้จะเป็น
```py
from abc import ABC, abstractmethod <--- (0)
from typing import Self, Any, Optional

class Shape(ABC): <--- (1)
    @abractmethod <--- (2)
    def area(self: Self,) -> Optional[float]: <--- (T1)
        pass <--- (3)

class Circle(Shape):
    def __init__(self, radius: int,):
        self.r = radius

    def area(self: Self,) -> float: <--- (4)
        return 3.14 * self.r ** 2

x = Circle(5)
print(x.area()) # 78.75
```
okay~~ เรามาแยกส่วนการทำงานของ Object กันดีกว่าาา~

**(0)** ส่วนที่ 0 คือแค่เป็นการนำ library `ABC` แล้วเรียนใช้เฉพาะ `ABC` และ `abstractmethod`.

> from typing import Self, Any, `Optional` อันนี้มันเป็นแค่บ่นบอกว่า สิ่งๆนั้นจะสามารถเป็นได้ทั้ง `NoneType` หรือ `Types` ที่เราให้ใช้~ ณ **(T1)** ก็จะหมายความว่าเป็นได้ทั้ง `NoneType` หรือ `float`
- `Optional` เป็น แค่ `Strict Typing` สำหรับคนที่ใช้ `Strict Typing` หรือ `pyright` module แทน `pylance` module

**(1)** ส่วนนี้จะเป็น การบอกว่า class `Shape` นี้อยู่ใน `ABC` หรือ `Abstract-Based Class` ง่ายๆคือการนับว่า class `Shape` นี้เป็น `Abstract Class` นั้นแหละ~

**(2)** ส่วนนี้จำกันได้มั้ยว่า `@` บน `method` จะถูกเรียกว่าอะไร?

<f>[TextBox] <btn>Check</btn></f>
<!-- TODO: make user answer it and press check it check the answer If 'Answer' is 'Decorators' ignore-case and can 'Decorator' with no 's' too! is correct. and can passing to the next page below.-->

- สิ่งตรงนี้จะเป็นการประกาศ `Decorators` บนตัว method `area()` ว่าเป็น `Abstract Method`
> ซึ่ง `Abstract Method` อธิบายสั่นๆคือ method ที่ไม่มีส่วน `Body`~

ดังนั้นส่วนที่ **(3)** ก้จะบอกว่า ไม่มีส่วน Body ใดๆเลย~ คือ `pass`

**(4)** ส่วนนี้คือ**จำเป็นต้อง**มีถ้าเรายังใช้ class `Shape` เป็น **SuperClass** หรือ **ParentClass**
- เนื่องจากใน **SuperClass** มี `Abstract Method` ที่มีชื่อว่า `area()` ดังนั้นใน **SubClass** จำเป็นที่จะต้องมี `area()` method เหมือนกัน~

> Abstract Method มีไว้กันการเรียกใช้งาน **SuperClass** แบบตรงๆ
เราสามารถเขียนได้ 2 แบบนะ~

แบบแรกจะเป็น
```py
from abc import ABC, abstractmethod

class Shape(ABC):
    @abractmethod
    def area(self: Self,):
        pass
[...]
```

แบบที่ 2 จะเป็น
```py
class Shape:
    def area(self: Self,):
        raise NotImplementedError()
[...]
```

> ก้แล้วแต่ว่าเราจะใช้แบบไหนมันใช้ได้ทั้งคู่~ แค่แบบแรกจะเป็น `TypeError` แบบ 2 จะเป็น `NotImplementedError`~