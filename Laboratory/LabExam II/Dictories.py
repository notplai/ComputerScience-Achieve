from typing import Optional

# 1.1: หารายชื่อนักเรียกที่กกรอกเข้ามาแล้ return scores กลับออกไป
def findStudentScore(students: dict[str, int], name: str, /) -> Optional[int]:
    return students.get(name, None) # If that key exists returns values, but not returns None;

    try: # Try-catching (If key exist did Same) but when not will give you KeyError
         # and using except to catching and returns None instead!
        return students[name]
    except KeyError:
        return None # Performace is fastest when key exists, and slow if key not exist!
    
    return None if name not in athele else athele[name] # Pre-LookUp
    # if key existed just go into block statment and get value if not just returns None.
print(
    findStudentScore({
        "Tan": 23,
        "Idea": 42,
        "Ball": 37,
        "Plai": 15,
        "Pai": 27,
    }, "Tan")
)


# 1.2: กุจำไม่ได้เว้ยยย~


# 1.3: หารายชื่อนักเรียกที่ได้คะแนนสูงสุด
def findTop(atheles: dict[str, dict[str, int]], events_name: str, /) -> Optional[list[str]]:
    if events_name not in atheles: return None

    # vmax = max(atheles[events_name], key=lambda v: atheles[events_name][v])
    scores = atheles[events_name]
    vmax = max(scores.values())
    return [k for k, v in scores.items() if v == vmax]
print(
    findTop(
    {
        "C1": {
            "Plai": 92,
            "Ball": 108,
            "Idea": 102,
            "Tan": 112,
        },
        "C2": {
            "Pai": 123,
            "Kate": 123
        }
    },
    "C1"
    )
)
