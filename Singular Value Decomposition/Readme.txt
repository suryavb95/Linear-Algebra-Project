-------------------------------------------------------------------------------
Project Name SVD program
-------------------------------------------------------------------------------
Version 1.0
Release date: 03/16/2014
-------------------------------------------------------------------------------
Project state:
experimental
-------------------------------------------------------------------------------
Credits
	Surya Bhamidipati
	Aaron friesnam(CS 1331 TA)
-------------------------------------------------------------------------------
Project description

I have created a program that takes in a matrix and creates 
its singular value Decomposition. I used the JAMA package, 
especially the Matrix and the SingularValueDecomposition class.
The method takes in an array of doubles, and converts it to a matrix. 
It then uses the SVD class to find U, V and sigma. I then multiplied
the V and U transpose vectors and the multiplied sigma to find the 
best rank approximation. Depending on the value of k, it keeps doing this. 
-------------------------------------------------------------------------------
Dependencies:

-------------------------------------------------------------------------------
Documentation

-------------------------------------------------------------------------------
Installation instructions

To run this program, run the ImageSVD class, and enter the name of the image in 
the command prompt when running the file. Then input value of k. It will then alter
the image and siplay it. 
-------------------------------------------------------------------------------
Additional Notes

Make sure to install JAMA package, and also the language is JAVA. 
http://math.nist.gov/javanumerics/jama/
