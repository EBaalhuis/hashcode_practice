library(magrittr)
library(dplyr)
library(ggplot2)

input = scan("R/medium.in", what = character(), sep = "\n")
formats <- input[1]
input %<>% .[-1]
formats %<>% strsplit(split = " ") %>% unlist %>% as.integer()
nrow = formats[1]
ncol = formats[2]
min_ingredient = formats[3]
max_ingredient = formats[4]

x = 1:ncol
y = sapply(1:nrow, rep.int, times = ncol) %>% as.vector

z = input %>% sapply(
    . %>% strsplit("") %>% unlist
) %>% as.vector

data.frame(x = x, y = y, z = z) %>%
{
    ggplot(., aes(x = x, y = y, col = z)) + geom_point(alpha = .5)
}
