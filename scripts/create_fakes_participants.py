from faker import Faker
import random
import datetime

# Inicializar Faker con localización en español
fake = Faker('es_ES')

# Función para generar fechas de nacimiento entre 12 y 21 años atrás
def generate_birthdate():
    today = datetime.date.today()
    start_date = today.replace(year=today.year - 21)
    end_date = today.replace(year=today.year - 12)
    birthdate = fake.date_between(start_date=start_date, end_date=end_date)
    return birthdate

# Generar 30 participantes con nombres y apellidos españoles
for _ in range(30):
    first_name = fake.first_name()
    last_name = fake.last_name()
    birth_date = generate_birthdate()
    print(f"Nombre: {first_name} {last_name}, Fecha de nacimiento: {birth_date}")
