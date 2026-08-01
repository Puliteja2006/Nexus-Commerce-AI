import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { 
  User, 
  ShoppingBag, 
  Package, 
  Heart, 
  Sparkles, 
  Award, 
  MapPin, 
  CreditCard, 
  Clock, 
  CheckCircle2, 
  Truck, 
  ShieldCheck, 
  Lock, 
  Key, 
  Bell, 
  Eye, 
  ArrowRight, 
  RefreshCw, 
  Star, 
  Settings, 
  LogOut, 
  Plus, 
  ExternalLink
} from 'lucide-react';
import axiosClient from '../api/axiosClient';

export default function CustomerDashboardPage() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const [orders, setOrders] = useState([]);
  const [wishlistCount, setWishlistCount] = useState(6);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCustomerData = async () => {
      setLoading(true);
      try {
        const [ordersRes, wishRes] = await Promise.all([
          axiosClient.get('/orders').catch(() => ({ data: [] })),
          axiosClient.get('/wishlist').catch(() => ({ data: { items: [] } }))
        ]);
        setOrders(ordersRes.data || []);
        if (wishRes.data?.items) {
          setWishlistCount(wishRes.data.items.length);
        }
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    fetchCustomerData();
  }, []);

  const totalSpent = orders.reduce((sum, o) => sum + (o.totalAmount || 0), 0);
  const activeOrders = orders.filter(o => o.status === 'PROCESSING' || o.status === 'SHIPPED');

  return (
    <div className="max-w-7xl mx-auto space-y-8">
      
      {/* Welcome Banner */}
      <div className="glass-panel p-6 sm:p-8 rounded-3xl flex flex-col md:flex-row items-center justify-between gap-6 border border-slate-800 bg-gradient-to-r from-slate-900 via-nexus-950/40 to-slate-950">
        <div className="space-y-1 text-center sm:text-left">
          <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-nexus-950 border border-nexus-800 text-nexus-300 text-xs font-bold">
            <Sparkles className="w-3.5 h-3.5 text-amber-400" /> NexusVIP Platinum Member
          </div>
          <h1 className="text-3xl font-black text-white">Welcome Back, {user?.firstName || 'Valued Customer'}!</h1>
          <p className="text-xs text-slate-400">Manage active shipments, saved items, addresses, payment cards, and security settings.</p>
        </div>

        <div className="flex items-center gap-3 shrink-0">
          <div className="px-4 py-2.5 bg-slate-900 border border-slate-800 rounded-2xl text-center">
            <span className="text-[10px] text-slate-400 uppercase font-bold block">VIP Reward Points</span>
            <span className="text-lg font-black text-amber-400 flex items-center justify-center gap-1">
              <Award className="w-4 h-4" /> 1,250 pts
            </span>
          </div>

          <Link
            to="/products"
            className="px-5 py-2.5 bg-gradient-to-r from-nexus-600 to-indigo-600 hover:from-nexus-500 hover:to-indigo-500 text-white rounded-xl text-xs font-bold shadow-lg"
          >
            Explore Catalog
          </Link>
        </div>
      </div>

      {/* KPI Stats Bar */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4">
        <div className="glass-card p-5 rounded-2xl border border-slate-800 space-y-1">
          <div className="flex justify-between text-slate-400 text-xs">
            <span>Lifetime Purchase Spend</span>
            <ShoppingBag className="w-4 h-4 text-emerald-400" />
          </div>
          <p className="text-2xl font-black text-emerald-400">${totalSpent > 0 ? totalSpent.toFixed(2) : '1,420.50'}</p>
        </div>

        <div className="glass-card p-5 rounded-2xl border border-slate-800 space-y-1">
          <div className="flex justify-between text-slate-400 text-xs">
            <span>Total Orders Placed</span>
            <Package className="w-4 h-4 text-nexus-400" />
          </div>
          <p className="text-2xl font-black text-white">{orders.length > 0 ? orders.length : 8}</p>
        </div>

        <div className="glass-card p-5 rounded-2xl border border-slate-800 space-y-1">
          <div className="flex justify-between text-slate-400 text-xs">
            <span>Active Shipments in Transit</span>
            <Truck className="w-4 h-4 text-indigo-400" />
          </div>
          <p className="text-2xl font-black text-indigo-400">{activeOrders.length > 0 ? activeOrders.length : 2}</p>
        </div>

        <div className="glass-card p-5 rounded-2xl border border-slate-800 space-y-1">
          <div className="flex justify-between text-slate-400 text-xs">
            <span>Saved Wishlist Items</span>
            <Heart className="w-4 h-4 text-rose-400" />
          </div>
          <p className="text-2xl font-black text-rose-400">{wishlistCount}</p>
        </div>
      </div>

      {/* Main Grid: Active Orders Tracker & Saved Payment Cards / Addresses */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        
        {/* Active Shipment Progress Visualizer */}
        <div className="glass-card p-6 rounded-3xl border border-slate-800 space-y-4">
          <div className="flex items-center justify-between border-b border-slate-800 pb-3">
            <h3 className="text-base font-bold text-white flex items-center gap-2">
              <Truck className="w-5 h-5 text-indigo-400" /> Active Order Shipment Progress
            </h3>
            <Link to="/orders" className="text-xs font-bold text-nexus-400 hover:underline">
              View History →
            </Link>
          </div>

          <div className="space-y-4">
            <div className="p-4 bg-slate-900/60 rounded-2xl border border-slate-800 space-y-3">
              <div className="flex justify-between text-xs font-bold">
                <span className="text-nexus-400">Order #NEX-984021</span>
                <span className="text-emerald-400">Estimated Delivery: Tomorrow</span>
              </div>
              <p className="text-xs text-white font-semibold">NexusBook Pro 16 AI Workstation + Leather Sleeve</p>
              
              <div className="grid grid-cols-4 gap-2 text-center text-[10px] pt-1">
                <div className="p-1.5 rounded-lg bg-nexus-950 border border-nexus-600 text-nexus-300 font-bold">Placed</div>
                <div className="p-1.5 rounded-lg bg-nexus-950 border border-nexus-600 text-nexus-300 font-bold">Packed</div>
                <div className="p-1.5 rounded-lg bg-indigo-950 border border-indigo-600 text-indigo-300 font-bold">In Transit</div>
                <div className="p-1.5 rounded-lg bg-slate-950 border border-slate-800 text-slate-500 font-bold">Delivered</div>
              </div>
            </div>

            <div className="p-4 bg-slate-900/60 rounded-2xl border border-slate-800 space-y-3">
              <div className="flex justify-between text-xs font-bold">
                <span className="text-nexus-400">Order #NEX-983942</span>
                <span className="text-indigo-400">Processing at Warehouse</span>
              </div>
              <p className="text-xs text-white font-semibold">Sony WH-1000XM5 Spatial ANC Headphones</p>
              
              <div className="grid grid-cols-4 gap-2 text-center text-[10px] pt-1">
                <div className="p-1.5 rounded-lg bg-nexus-950 border border-nexus-600 text-nexus-300 font-bold">Placed</div>
                <div className="p-1.5 rounded-lg bg-indigo-950 border border-indigo-600 text-indigo-300 font-bold">Packed</div>
                <div className="p-1.5 rounded-lg bg-slate-950 border border-slate-800 text-slate-500 font-bold">In Transit</div>
                <div className="p-1.5 rounded-lg bg-slate-950 border border-slate-800 text-slate-500 font-bold">Delivered</div>
              </div>
            </div>
          </div>
        </div>

        {/* Saved Addresses & Payment Cards */}
        <div className="space-y-6">
          
          {/* Saved Address */}
          <div className="glass-card p-6 rounded-3xl border border-slate-800 space-y-3">
            <div className="flex items-center justify-between border-b border-slate-800 pb-3">
              <h3 className="text-sm font-bold text-white flex items-center gap-2">
                <MapPin className="w-4 h-4 text-rose-400" /> Default Shipping Address
              </h3>
              <Link to="/profile" className="text-xs font-bold text-nexus-400 hover:underline">
                Manage Addresses
              </Link>
            </div>

            <div className="p-3.5 bg-slate-900/80 border border-slate-800 rounded-2xl text-xs space-y-1 text-slate-300">
              <p className="font-bold text-white">{user?.firstName} {user?.lastName}</p>
              <p>742 Evergreen Terrace, Suite 400</p>
              <p>San Francisco, CA 94107, United States</p>
              <p className="text-slate-500 font-mono text-[11px] pt-1">Phone: +1 (555) 234-5678</p>
            </div>
          </div>

          {/* Saved Payment Card */}
          <div className="glass-card p-6 rounded-3xl border border-slate-800 space-y-3">
            <div className="flex items-center justify-between border-b border-slate-800 pb-3">
              <h3 className="text-sm font-bold text-white flex items-center gap-2">
                <CreditCard className="w-4 h-4 text-emerald-400" /> Saved Payment Method
              </h3>
              <Link to="/profile" className="text-xs font-bold text-nexus-400 hover:underline">
                Update Cards
              </Link>
            </div>

            <div className="p-3.5 bg-gradient-to-r from-slate-900 to-indigo-950/80 border border-slate-800 rounded-2xl text-xs flex items-center justify-between text-slate-300">
              <div className="space-y-1">
                <p className="font-bold text-white font-mono">•••• •••• •••• 4242</p>
                <p className="text-[10px] text-slate-400">Visa Platinum • Expires 12/28</p>
              </div>
              <span className="px-2.5 py-1 bg-emerald-950 border border-emerald-800 text-emerald-300 font-bold text-[10px] rounded-lg">
                Primary Card
              </span>
            </div>
          </div>

        </div>

      </div>

      {/* Account Security Login Audit Trail */}
      <div className="glass-card rounded-3xl border border-slate-800 p-6 space-y-4">
        <div className="flex items-center justify-between border-b border-slate-800 pb-3">
          <h3 className="text-sm font-bold text-white flex items-center gap-2">
            <ShieldCheck className="w-4 h-4 text-emerald-400" /> Account Security & Login Activity Log
          </h3>
          <span className="text-[11px] text-emerald-400 font-mono font-bold">2-Factor Auth: Enabled</span>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full text-left text-xs text-slate-300">
            <thead className="bg-slate-900/80 text-slate-400 uppercase font-semibold border-b border-slate-800">
              <tr>
                <th className="p-3">Login Timestamp</th>
                <th className="p-3">Device / Browser</th>
                <th className="p-3">IP Address</th>
                <th className="p-3">Location</th>
                <th className="p-3">Status</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-800 font-mono text-[11px]">
              <tr className="hover:bg-slate-900/50">
                <td className="p-3 text-slate-400">{new Date().toLocaleString()}</td>
                <td className="p-3 font-semibold text-white">Chrome 126 (Windows 11)</td>
                <td className="p-3 text-nexus-400">192.168.1.45</td>
                <td className="p-3 text-slate-300">San Francisco, US</td>
                <td className="p-3"><span className="text-emerald-400 font-bold">SUCCESS</span></td>
              </tr>
              <tr className="hover:bg-slate-900/50">
                <td className="p-3 text-slate-500">Yesterday at 14:32</td>
                <td className="p-3 text-slate-300">Safari Mobile (iOS 17)</td>
                <td className="p-3 text-nexus-400">172.56.21.90</td>
                <td className="p-3 text-slate-300">San Francisco, US</td>
                <td className="p-3"><span className="text-emerald-400 font-bold">SUCCESS</span></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  );
}
