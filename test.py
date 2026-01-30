import unittest

def is_palindrome(s):
    return s == s[::-1]

class TestPalindrome(unittest.TestCase):
    def test_palindrome_true(self):
        self.assertTrue(is_palindrome("racecar"))
        self.assertTrue(is_palindrome("madam"))
        self.assertTrue(is_palindrome(""))

    def test_palindrome_false(self):
        self.assertFalse(is_palindrome("python"))
        self.assertFalse(is_palindrome("hello"))

    def test_palindrome_case_sensitive(self):
        self.assertFalse(is_palindrome("Racecar"))

if __name__ == "__main__":
    unittest.main()