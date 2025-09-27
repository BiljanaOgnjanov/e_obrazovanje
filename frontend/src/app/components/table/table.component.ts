import { Component, EventEmitter, Input, Output, TemplateRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRole } from '../../services/admin.service';

export interface TableColumn {
  value: string;
  label: string;
  customTemplate?: TemplateRef<any>;
}

@Component({
  selector: 'app-table',
  imports: [CommonModule, FormsModule],
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {
  @Input() title: string = '';
  @Input() columns: TableColumn[] = [];
  @Input() data: any[] = [];
  @Input() searchable: boolean = false;
  @Input() adminRole?: AdminRole;

  searchText: string = '';

  @Output() edit = new EventEmitter<any>();
  @Output() remove = new EventEmitter<any>();

  filteredData(): any[] {
    if (!this.searchText) return this.data;

    const search = this.searchText.toLowerCase();

    return this.data.filter((row) =>
      this.columns.some((col) => {
        const cellValue = row[col.value];
        return cellValue?.toString().toLowerCase().includes(search);
      })
    );
  }
}
