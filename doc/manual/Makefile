NAME=civl-manual
SRC=$(NAME).tex
BIB=civl.bib

$(NAME).pdf: $(SRC) $(BIB)
	pdflatex $(SRC)
	bibtex $(NAME)
	pdflatex $(SRC)
	pdflatex $(SRC)

clean:
	rm -f *.aux *.log *.out *.pdf *.blg *.bbl *.toc
