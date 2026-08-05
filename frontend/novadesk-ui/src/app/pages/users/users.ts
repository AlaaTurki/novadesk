import { Component, OnInit, ViewChild } from '@angular/core';

import { CommonModule } from '@angular/common';

import { UserService } from '../../core/services/user';

import { User } from '../../core/models/user';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';

import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { MatSort, MatSortModule } from '@angular/material/sort';

import { MatButtonModule } from '@angular/material/button';

import { MatIconModule } from '@angular/material/icon';

import { MatCardModule } from '@angular/material/card';


import { MatInputModule } from '@angular/material/input';

import { MatFormFieldModule } from '@angular/material/form-field';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


@Component({
  selector: 'app-users',

  standalone: true,

  imports: [

    CommonModule,

    MatTableModule,

    MatPaginatorModule,

    MatSortModule,

    MatButtonModule,

    MatIconModule,

    MatCardModule,

    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,

  ],

  templateUrl: './users.html',

  styleUrl: './users.scss'
})
export class Users implements OnInit {

  displayedColumns: string[] = [

    'username',

    'email',

    'role',

    'actions'

  ];

  dataSource = new MatTableDataSource<User>();

  loading = true;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {

    this.loadUsers();

  }

  applyFilter(event: Event): void {

  const filterValue = (event.target as HTMLInputElement).value;

  this.dataSource.filter = filterValue.trim().toLowerCase();

} 

  loadUsers(): void {

    this.loading = true;

    this.userService.getAll().subscribe({

      next: users => {

        this.dataSource.data = users;

        this.dataSource.paginator = this.paginator;

        this.dataSource.sort = this.sort;

        this.loading = false;

      },

      error: error => {

        console.error(error);

        this.loading = false;

      }

    });

  }

  delete(id: string): void {

    if (!confirm('Delete this user?')) {

      return;

    }

    this.userService.delete(id).subscribe({

      next: () => {

        this.loadUsers();

      }

    });

    

  }

}