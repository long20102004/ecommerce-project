import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../../services/product-service.service';
import { Product } from '../../common/product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{
  products: Product[] = [];
  constructor (private productService: ProductServiceService){

  }
  // same to @PostConstruct in SpringBoot
  ngOnInit(): void{
    this.listProduct();
  }
  listProduct(){
    this.productService.getProductList().subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
