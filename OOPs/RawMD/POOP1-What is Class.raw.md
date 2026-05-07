# สรุป OOP แบบง่ายๆ~ (มั้ง~)
OOP หรือ Object-Oriented Programming จำเป็นต้องมองสิ่งที่เราจะเขียนเป็น Objectๆ หนึง~ และแตกการทำงานและความสามารถของ Object นั้นออกมาเป็น BluePrint ให้ได้
จะ Recap ให้เข้าใจได้ง่ายที่สุดนะ ในการ programming เท่านั้น
```py
from typing import Self, Any <--- (0)

class Brain: <--- (1)
    def __init__(self: Self, nameOf: str, /) -> None: <--- (2)
        self.owner = nameOf <--- (3)
        self.memories = [] <--- (4)
    def add2mem(self: Self, memory: Any, /) <--- (5)
        self.memories.append(memory) <--- (6)

foo = Brain('Cat') <--- (7)
foo.add2mem('Owner is NotPlai') <--- (8)
print(foo.memories) <--- (9)
```

**(0)** จริงๆแค่ดึง Libraries Typing ใน Python มาใช้ สำหรับให้เข้าใจได้ง่ายมากขึ้นเท่านั้น ไม่ต้อง Import ตามก้ได้~
- Any คือหมายถึงข้อมูลของสิ่งที่เราเอา label นี้ไปแปะจะเป็น'อะไรก้ได้'~
- Self จริงๆมันดูปวดหัวนะ ง่ายๆคือมันแค่ label สิ่งนั้นว่าเป็น arguments ที่จะบอกว่ามันเป็นสิ่งที่เรียกใช้งานตัวเอง~

**(1)** ส่วนนี้จะเป็นส่วนสำหรับ สร้าง Class หรือง่ายๆมันจะเป็น BluePrint อันหนึ่ง ใช้สำหรับการสร้าง Object จริงๆขึ้นมา~ *การสร้าง Class จะยังไม่นับเป็น Object จริงๆ เป็นแค่ BluePrint สำหรับการสร้าง Objects จริงๆเท่านั้น~*

**(2)** ส่วนนี้จะเป็น constructor method *โปรดจำไว้ว่า method อะไรก้ตามที่ underscore หน้าหลัง 2 ตัวจะนับเป็น special methods มันไม่ใช่ method ปกติ~* ในที่นี้ special method อันนี้จะเป็น constructor method สำหรับใน Python จะต้องเขียนว่า *\_\_init\_\_* เท่านั้นถึงนับ เป็น header method หรือ method ที่จะนำข้อมูลเข้ามาใช้งาน~
- ในส่วน \_\_init\_\_`(self: Self, nameOf: str, /)` จะเรียกว่า parameters ใน parameters ของ method นี้จะมีสิ่งที่จะนำเข้ามาใช้งานเรียกว่า **arguments** หรือ **args** สำหรับตัวย่อ~ ในที่นี้มี**แค่** `nameOf` ที่จะนำเข้ามา**เท่านั้น**~ *เนื่องจาก **self** จะนับเป็น Self@ClassObject หรือเป็นตัวสำหรับเรียกใช้งานข้อมูลหรือ method ใน Class ตัวเอง~*
- `self` คือจะเปลี่ยนชือเป็นอะไรก้ไ้ด้นะแต่มันจำเป็นต้องอยู่ตัวแรกใน parameters นะ~ เช่น `__init__(s, nameOf)`แค่เรียกใช้ให้ถูกก้พอ~
- และ constructor method **จะต้อง** returns เป็น NoneType เท่านั้น~
> สำหรับภาษาอื่นจะไม่ต้องประกาศและใช้ this แทน เช่น Java~

**(3)** ส่วนนี้จะเป็นการนำข้อมูลจากที่เรารับเข้ามาเข้าไปในตัว Class หรือ Object นั้นๆ ณ ที่นี้จะเป็น `self.owner = nameOf` คือแค่นำข้อมูลจาก `nameOf` เข้าไปใน `owner` ในตัวมันเอง *พวกนี้คือ variables เป็นเหมือนการประกาศ variable ทั่วไปนั้นแหละ~* ส่วนนี้จะเรียกว่า `Instance Attribute` นะ
- `self.*` อย่างที่บอกว่า self มันเป็น special arguments ดังนั้นในทั้ง blueprint ของ class นี้จะมองเห็นแลละเรียกใช้งานได้~ (แปลไทยเป็นไทยคือ เราสามารถเรียนใช้งานข้าม method ได้ตราดใดที่ยังอยู่ใน class เดียวกันและใน method ที่ต้องการใช้เรียกใช้ self เป็น arguments ด้วย~)

**(4)** ส่วนนี้จะเป็นการประกาศ attribute ที่จะนำไปใช้งานเฉพาะใน Class/Object ตัวเอง จะเรียกว่า `Class Attribute` นะ ณ ที่นี้จะเป็น memories ที่เป็น List ว่างๆ~

**(5)** ส่วนนี้คือส่วนที่เป็น method ชื่อว่า `add2mem` จริงๆถ้ายังมองไม่ออกจะมองเป็น functionๆ หนึงก้ได้~

**(6)** นี้จะเป็นคำสั่งใน method นี้เฉยๆเราสามารถเขียนอะไรก้ได้เหมือนตามปกติเลยย~ ณ ที่นี้จะเป็น `self.memories.append(memory)` ก้แค่รับ ข้อมูล `memory` มาจาก parameter ของ method `add2mem` และในข้อมูลนี้ไป<u>ใส่</u>ต่อท้ายใน List ของ memories ใน Object ของตัวเอง
> ที่บอกว่า "ใส่" ผิดคือ `typing.List.append(x: Any)` เป็นการนำข้อมูลจาก args `x` ไปต่อท้ายใน `List` 
- ส่วนนี้เป็นจะส่วนบรรทัดสุดทายที่เราสร้าง class object เป็นแบบ blueprint นะ~ เพราะมันเป็นบรรทัดสุดท้ายที่ indentation มันอยู่ใน Class Object นี้นะ~

**(7)** อันนี้จะเป็นการสร้าง Object ขึ้นมาละพร้อม label Object นั้น ณ ที่นี้เป็น `foo = Brain('Cat')` คือเป็นการสร้าง Object Brain จาก `Brain` BluePrint(Class) พร้อมใส่ชื่อเจ้าของสมองนี้เป็นของ `Cat` หรือแมว และ label สมองของแมวตัวนี้ว่า `foo`~
- Outline: `foo -> Brain('Cat') -> (Class) Brain.__init__("Cat")`

**(8)** คือการใช้ Brain Object หรือสมองของแมวที่เรา label ไว้ว่า `foo` มาใช้~ และเราก้เรียกใช้ `add2mem` Method ใน Brain Object ของแมวที่เรา label ว่า `foo` 
- Outline: `foo.add2mem("Author is NotPlai") -> foo -> Brain('Cat') -> (Method) Brain('Cat').add2mem('Author is NotPlai') -> (6) self.memories.append(memory)`

**(9)** ส่วนนี้แค่เอาข้อมูลจาก `memories` ใน Object Brain ของแมวตัวนั้นออกมาว่ามีอะไรเก็บไว้อยู่~ อันที่เราประกาศ `self.memories = []` หรือส่วนที่ (4)
ก้จะได้
```
Owner is NotPlai
```
จบ~

Noted กันนิดนุงนะว่า
- ใน Python วิธีการสร้าง constructor method จะต้องเขียน `__init__` method ขี้นมา
```py
class foo:
    def __init__(self,): <---
        pass 
```
- แล้วก้ใน Python มี Private/Public Class/Variable ด้วยนะ
  - เราเคยบอกใช่มั้ยว่า *โปรดจำไว้ว่า method อะไรก้ตามที่ underscore หน้าหลัง 2 ตัวจะนับเป็น special methods มันไม่ใช่ method ปกติ~* ใช่มั้ยย~
  - ซึ่ง การ underscore มันจะแบบบ้าบอนึดนุงนะ~
  - การ underscore หน้า method/variable 1 ตัวกับ method/variable อะไรก้ได้ที่ไม่ใช่ special method และ special variable จะนับเป็น `protected method` คือจะไม่สามารถเรียกใช้งานใน global scope ได้ แต่ยังสามารถใช้ใน class, subclass(childclass) และใน package(file) เดียวกันได้
  - ส่วนการ underscore หน้า method 2 ตัวจะเป็นการบอกว่า method นี้จะเป็น `private` คือจะไม่สามารถเรียกใช้งานจากนอก class ได้
```py
class foo:
    def a(self,): <--- Public Method
        print("Kam")
    def _a(self,): <--- Protected Method
        print("Jigsaw")
    def __a(self,): <--- Private Method
        print("JaJaa")
foo().a() # Kam
foo()._a() # Jigsaw
foo().__a() # AttributeError: 'foo' object has no attribute '__a'
```
- ซึ่งใน Python จะมี Self Variable อยู่ทั้งหมด 2 แบบ คือ
  - `Instance Attribute` คือ Attribute ที่เรารับเข้ามาผ่าน methods
  - `Class Attribute` คือ Attribute ที่สร้างได้เองจากการเรียกใช้งาน method นั้นๆ~
```py
class foo:
    def __init__(self, a: int):
        self.a = a <--- Instance Attribute
        self.b = 5 <--- Class Attribute
```
- `self` ไม่ จำเป็นต้องมีทุก method ซึ่งเราจะเรียก method ที่ไม่มี `self` เป็น args เป็น `static method` ใน python จะต้องเขียน
```py
class foo:
    @staticmethod <---
    def a(x: int):
        return x+3

print(foo.a(2)) # 5
```
จะเห็นได้ว่า method ที่เป็น static method จะไม่ต้อง `foo().a(2)` จะเป็น `foo.a(2)` เพราะการที่เราใช้ `foo()` จะเป็นการเรียกใช้งาน class/object ตัวมันเองมันจึงมีการ returns Self เกิดขึ้น แบบ
```py
from typing import Self
class foo:
    def __init__(self,) -> Self:
        self.y = 5
    @staticmethod
    def a(x: int):
        return x+3
    @staticmethod
    def b(x: int):
        return self.y-x
foo().a(2) # 5 (run passed, just 'a' method don't need self)
foo.b(2) # NameError: name 'self' is not defined
```
> พวก `@` อยู่ด้านบน method จะเรียกว่า **Decorators** ทั้งหมดนะ~