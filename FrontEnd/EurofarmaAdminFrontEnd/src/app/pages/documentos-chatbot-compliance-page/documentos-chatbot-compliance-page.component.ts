import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { EuroComplianceChatbotDocsService } from '../../services/euro-compliance-chatbot-docs.service';
import { DocumentChatbot } from '../../interfaces/documentInterfaces';
import { TableDocumentComponent } from '../../components/table-document/table-document.component';
import { MatProgressSpinner } from '@angular/material/progress-spinner';


@Component({
  selector: 'app-documentos-chatbot-compliance-page',
  standalone: true,
  imports: [TableDocumentComponent,MatProgressSpinner],
  templateUrl: './documentos-chatbot-compliance-page.component.html',
  styleUrl: './documentos-chatbot-compliance-page.component.css'
})
export class DocumentosChatbotCompliancePageComponent {
  displayedColumns: string[] = ['id', 'Título','Data de criação','actions'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  documentData:DocumentChatbot[] = []
  isLoading: boolean = true;

  constructor(private complianceService: EuroComplianceChatbotDocsService) {
    this.complianceService.findAllDocs().subscribe({
      next: (document) => {
        this.documentData = document.documents;
        this.dataSource.data = this.documentData;
        this.isLoading = false;  
      },
      error: (error) => {
        console.error('Failed to load documents:', error);
        this.isLoading = false; 
      }
    });
   }
  

   deleteDoc = (id: string): void => {
    this.complianceService.deleteDoc(id).subscribe({
      next: () => {
        this.documentData = this.documentData.filter(doc => doc.id !== id);
        this.dataSource.data = this.documentData;
      },
      error: (error: any) => {
        if (error.status === 404) {
          console.error('Document not found or already deleted.');
        } else if (error.status === 403) {
          console.error('Authorization error. Check your API key and permissions.');
        } else {
          console.error('An error occurred:', error);
        }
      },
    });
  }
}
