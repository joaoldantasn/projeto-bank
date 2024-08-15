import { useCallback } from 'react';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

const useGeneratePDF = () => {
  const gerarPDF = useCallback((elementId, fileName = 'documento.pdf') => {
    const input = document.getElementById(elementId);
    if (input) {
      html2canvas(input, { scale: 2 }).then((canvas) => {
        const imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF();
        const imgWidth = 210; // A4 width in mm
        const imgHeight = (canvas.height * imgWidth) / canvas.width;
        pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);
        pdf.save(fileName);
      });
    }
  }, []);

  return gerarPDF;
};

export default useGeneratePDF;
