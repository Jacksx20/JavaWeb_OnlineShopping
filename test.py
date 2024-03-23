def is_armstrong_number(num):
    # Calculate the number of digits in the number
    num_of_digits = len(str(num))
    
    # Calculate the sum of the digits raised to the power of the number of digits
    sum_of_digits = sum(int(digit) ** num_of_digits for digit in str(num))
    
    # Check if the number is an Armstrong number
    if sum_of_digits == num:
        return True
    else:
        return False

# List to store the Armstrong numbers
armstrong_numbers = []

# Iterate through numbers below 100000
for num in range(1, 100000):
    if is_armstrong_number(num):
        armstrong_numbers.append(num)

# Calculate the sum of the Armstrong numbers
sum_of_armstrong_numbers = sum(armstrong_numbers)

# Print the Armstrong numbers and their sum
print("Armstrong numbers below 100000:")
print(armstrong_numbers)
print("Sum of Armstrong numbers:", sum_of_armstrong_numbers)