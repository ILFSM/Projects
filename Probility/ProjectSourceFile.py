import pandas as pd             #pandas for work with excel tables 
import numpy as np              # numpy for numerical calculations
import matplotlib.pyplot as plt # matplotlib for graphs
import seaborn as sns           # seaborn for build a visualizations 
from scipy import stats         # scipy for statistical operations 

# Loading dataset
dataset = pd.read_csv("vgsales.csv")
# print first 5 data after loading delete!!!!!!!!!!!!!!!!!!
print(dataset.head())
# get all sales in variable for future using
global_sales = dataset['Global_Sales']

# end 1 section starting  descriptive stat
 
mean = global_sales.mean()
median = global_sales.median()
variance = global_sales.var()
deviation = global_sales.std()
error = deviation / np.sqrt(len(global_sales)) # square root of each element before devise

print("mean: " + str(mean) )
print("median:" + str(median))
print("variance: " + str(variance))
print("standard Deviation: " + str(deviation))
print("standard Error: "  + str(error))

# 2nd section data visualition with matplotlib for graph and seaborn for visualition

#size
plt.figure(figsize=(12, 5))
# histogram
plt.subplot(1, 2, 1)
sns.histplot(global_sales, bins=40, kde=True)
plt.title("Histogram of Global Sales")
# boxplot
plt.subplot(1, 2, 2)
sns.boxplot(global_sales)
plt.title("Boxplot of Global Sales")

#3 section confidence interval

confidence = 0.95
size = len(global_sales)
h = error * stats.t.ppf((1 + confidence) / 2, size - 1)

ci_mean = (float(mean - h), float(mean + h))
ci_low, ci_high  = stats.chi2.interval(confidence, size - 1, loc=0, scale=variance/(size - 1))
ci_low = float(ci_low)
ci_high = float(ci_high)
print("95% confidence intervals for mean: " + str(ci_mean))
print("95% confidence intervals for variance: " + "(" + str(ci_low) + ", " + str(ci_high) + ")")

#4 section 
z = stats.norm.ppf(0.95)  # 90 -> 0.95 
E = 0.1  # hata payi
sample_size = (z * deviation / E) ** 2
print("Required sample size:" + str(sample_size))

#5 test section

t_stat, p_val = stats.ttest_1samp(global_sales, 1.0)

print("t-statistic: " + str(t_stat))
print("p-value: " + str(p_val))

if p_val < 0.05:
    print("Reject the null hypothesis.")
else:
    print("Fail to reject the null hypothesis.")


#\ show graph
plt.show()